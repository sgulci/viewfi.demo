package net.sahin.springvw.campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@EqualsAndHashCode
@ToString
@Slf4j
@Document(collection = "campaigns")
public class Campaign  implements Serializable {

    public String id;
    public String name;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    private boolean deleted = false;
    public String organizationId;
    public String accountId;
    public Campaign campaign;
    private ManagedState state = ManagedState.DRAFT;
    public boolean paused;
    public boolean archived;
    public boolean isSendedMailForEndedCampaign;
    public static final Opengraph OG_EMPTY = new Opengraph( "https://i.vimeocdn.com/filter/overlay", 0, 0 );
    public Opengraph opengraph = OG_EMPTY;
    private static Map<ManagedState, Set<ManagedState>> transitions = Map.of(
            ManagedState.DRAFT, Set.of( ManagedState.DRAFT, ManagedState.LIVE, ManagedState.ENDED ),
            ManagedState.LIVE, Set.of( ManagedState.LIVE, ManagedState.ENDED ),
            ManagedState.ENDED, Set.of( ManagedState.ENDED )
    );


    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    public String starts;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    public String ends;
    public String url;
    public String headline; //new?
    public String displayName;

    public Type type;

    public int numberOfRewards;

    public String explanation;
    public String email;

    public String optionalData1;
    public String optionalData2;
    public String optionalData3;
    public String unlockActionLink;
    public String unlockActionText;
    public String termsAndConditions;

    public List<String> countries = new ArrayList<>();
    public boolean countriesIncluded = true;

    public int completionPercentage = 75;
    public String trackingPixel;
    public String crmCampaignName;

    public List<String> analyticsEmails = new ArrayList<>();
    public NotificationFrequency analyticsEmailFrequency = NotificationFrequency.DAILY;
    public List<String> dataEmails = new ArrayList<>();
    public NotificationFrequency dataEmailFrequency = NotificationFrequency.DAILY;

    // competition
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    public String competitionDrawDate;
    // end competition

    // link
    public String linkRewardLink;
    // end link

    //coupon
    public String couponCode;
    //end coupon

    //freeform
    public String freeformPerkExplanation;
    //end freeform

    //web3
    public String blockChain;
    public String smartContactAddress;
    public String tokenTicker;
    public String walletCompatability;
    //end web3

    public InteractiveQuestion interactiveQuestion;

    public String overlayText;
    public String couponGroupId;
    public VIDEOSOURCE videoSource;
    public boolean allowReferrals;

    public boolean autoPlay = false;
    public LANGUAGE language = LANGUAGE.EN;
    public String perkWording = "PERK";
    public String privacyLink;
    public String widgetId;
    public String senderEmail;
    public String emailPropId;

    public boolean showFacebookLogin = true;
    public boolean showGoogleLogin = true;
    public boolean showEmailLogin = true;

    public List<AdditionalTask> additionalTasks;

    public boolean automatedEmailSending = true;

    public Campaign() {
    }

    public Campaign( String name, String url ) {
        this( null, name, url );
    }

    public Campaign( String id, String name, String url ) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @JsonIgnore
    public boolean isScheduledForNow() {
        return isStarted() && isNotEnded();
    }

    @JsonIgnore
    public boolean isStarted() {
        if( starts == null ) return false;
        DateTime dt = new DateTime(starts);
        var date = new DateTime( dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0 );
        var utc = DateTime.now() ;
        var now = new DateTime( utc.getYear(), utc.getMonthOfYear(), utc.getDayOfMonth(), 1, 0 );
        return now.isAfter( date );
    }


    @JsonIgnore
    public boolean isNotEnded() {
        if( ends == null ) return false;
        DateTime dt = new DateTime(ends);
        var date = new DateTime( dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 23, 59 );
        var utc = DateTime.now();
        var now = new DateTime( utc.getYear(), utc.getMonthOfYear(), utc.getDayOfMonth(), 0, 0 );
        return date.isAfter( now );
    }

    public boolean countryAllowed( String country ) {
        return countries.isEmpty()
                || ( countriesIncluded && countries.contains( country ) )
                || ( !countriesIncluded && !countries.contains( country ) );
    }


    public Campaign transitionTo( ManagedState state ) throws IllegalStateException {
        if( !isTransitionPossible( state ) )
            throw new IllegalStateException( "transition of campaign " + campaign.id + " from " + this.state + " to " + state + " is not allowed" );
        this.state = state;
        return this;
    }

    public boolean isTransitionPossible( ManagedState state ) {
        return transitions.get( this.state ).contains( state );
    }

    public ManagedState getManagedState() {
        return state;
    }

    public class View implements Serializable {

        public Campaign getCampaign() {
            return campaign;
        }

        public String getOrganizationId() {
            return organizationId;
        }

        public String getAccountId() {
            return accountId;
        }

        public ActualState getActualState() {
            return archived ? ActualState.ARCHIVED
                    : paused ? ActualState.PAUSED
                    : switch( state ) {
                case DRAFT -> ActualState.DRAFT;
                case LIVE -> campaign.isStarted() && campaign.isNotEnded()
                        ? ActualState.LIVE
                        : campaign.isNotEnded()
                        ? ActualState.SCHEDULED
                        : ActualState.ENDED;
                case ENDED -> ActualState.ENDED;
            };
        }

        public boolean isPaused() {
            return paused;
        }

        public boolean isArchived() {
            return archived;
        }

        public ManagedState getState() {
            return state;
        }

        public String getIconUrl() {
            return opengraph.imageUrl;
        }

        public String getPreviewUrl() {
            return opengraph.imageUrl;
        }
    }

    public Campaign fetchOpengraphData() {
        if( campaign.url != null )
            this.opengraph = Opengraph.of( campaign.url ).orElse( OG_EMPTY );
        return this;
    }

    public enum ManagedState {
        DRAFT,
        LIVE,
        ENDED,
    }

    public enum ActualState {
        DRAFT,
        SCHEDULED,
        LIVE,
        PAUSED,
        ENDED,
        ARCHIVED
    }

    public enum Type {
        COMPETITION,
        COUPON,
        LINK,
        FREEFORM,
        MULTICOUPON,
        MULTILINK,
        CHARITY,
        WEB3
    }

    public enum VIDEOSOURCE {
        YOUTUBE,
        VIMEO,
        CDN,
        FACEBOOK
    }

    public enum NotificationFrequency {
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY
    }

    public enum LANGUAGE {
        EN,
        TH,
        ZH,
        AR
    }
}
