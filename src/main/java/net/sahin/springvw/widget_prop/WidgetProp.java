package net.sahin.springvw.widget_prop;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Document(collection = "widgetProps")
public class WidgetProp implements Serializable {

    public String id;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    public boolean deleted = false;
    public String name;
    public String organizationId;
    public String userId;

    public Text text;
    public Warning warning;
    public Title title;
    public Button button;
    public Label label;
    public Link link;

    public WidgetProp( String id, String name, String organizationId, String userId,
                       Text text, Warning warning, Title title, Button button,
                       Label label, Link link ) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.userId = userId;
        this.text = text;
        this.warning = warning;
        this.title = title;
        this.button = button;
        this.label = label;
        this.link = link;
    }


    public WidgetProp() {
    }

    @ToString
    @EqualsAndHashCode
    public static class Label implements Serializable {

        public String firstName;
        public String lastName;
        public String email;
        public String mobile;
        public String tickToAgreeToOur;
        public String termsAndConditions;

        public Label( String firstName, String lastName, String email,
                      String mobile, String tickToAgreeToOur, String termsAndConditions ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.mobile = mobile;
            this.tickToAgreeToOur = tickToAgreeToOur;
            this.termsAndConditions = termsAndConditions;
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class Button implements Serializable {

        public String claimPerk;
        public String continueWatching;
        public String closeThisTab;
        public String submit;
        public String loginWithFacebook;
        public String loginWithGoogle;
        public String continueWithEmail;
        public String cntinue;

        public Button( String claimPerk, String continueWatching,
                       String closeThisTab,  String loginWithFacebook, String loginWithGoogle,
                       String continueWithEmail, String cntinue ) {

            this.claimPerk = claimPerk;
            this.continueWatching = continueWatching;
            this.closeThisTab = closeThisTab;
            this.loginWithFacebook = loginWithFacebook;
            this.loginWithGoogle = loginWithGoogle;
            this.continueWithEmail = continueWithEmail;
            this.cntinue = cntinue;
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class Title implements Serializable {

        public String perkUnlocked;
        public String learnMoreAbout;
        public String details;
        public String watchTheVideo;
        public String claimYourPerk;
        public String perkUnavailable;
        public String answerThisQuestion;
        public String optionalQuestions;
        public String termsAndConditions;
        public String shareWithFriends;
        public String unlockedCompetition;
        public String exclusiveCoupon;
        public String unlockedCoupon;
        public String unlockedLink;
        public String unlockedWrongAnswer;

        public Title( String perkUnlocked, String learnMoreAbout, String details,
                      String watchTheVideo, String claimYourPerk,
                      String perkUnavailable,String answerThisQuestion, String optionalQuestions,
                      String termsAndConditions, String shareWithFriends, String unlockedCompetition,
                      String exclusiveCoupon, String unlockedCoupon, String unlockedLink,
                      String unlockedWrongAnswer ) {

            this.perkUnlocked = perkUnlocked;
            this.learnMoreAbout = learnMoreAbout;
            this.details = details;
            this.watchTheVideo = watchTheVideo;
            this.claimYourPerk = claimYourPerk;
            this.perkUnavailable = perkUnavailable;
            this.answerThisQuestion = answerThisQuestion;
            this.optionalQuestions = optionalQuestions;
            this.termsAndConditions = termsAndConditions;
            this.shareWithFriends = shareWithFriends;
            this.unlockedCompetition = unlockedCompetition;
            this.exclusiveCoupon = exclusiveCoupon;
            this.unlockedCoupon = unlockedCoupon;
            this.unlockedLink = unlockedLink;
            this.unlockedWrongAnswer = unlockedWrongAnswer;
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class Warning implements Serializable {

        public String fillTheOptionalData;
        public String tickToAgree;

        public Warning( String fillTheOptionalData, String tickToAgree ) {
            this.fillTheOptionalData = fillTheOptionalData;
            this.tickToAgree = tickToAgree;
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class Text implements Serializable {

        public String watchWithoutSkipping;
        public String poweredBy;
        public String perkAlreadyClaimed;
        public String termsAndConditions;
        public String unlocksIn;
        public String watchForReward;
        public String unlockedCompetition;
        public String unlockedCoupon;
        public String unlockedLink;
        public String unlockedWrongAnswer;

        public Text( String watchWithoutSkipping, String poweredBy, String perkAlreadyClaimed,
                     String termsAndConditions,
                     String unlocksIn, String watchForReward, String unlockedCompetition,
                     String unlockedCoupon, String unlockedLink, String unlockedWrongAnswer ) {

            this.watchWithoutSkipping = watchWithoutSkipping;
            this.poweredBy = poweredBy;
            this.perkAlreadyClaimed = perkAlreadyClaimed;
            this.termsAndConditions = termsAndConditions;
            this.unlocksIn = unlocksIn;
            this.watchForReward = watchForReward;
            this.unlockedCompetition = unlockedCompetition;
            this.unlockedCoupon = unlockedCoupon;
            this.unlockedLink = unlockedLink;
            this.unlockedWrongAnswer = unlockedWrongAnswer;
        }
    }

    @ToString
    @EqualsAndHashCode
    public static class Link implements Serializable {

        public String learnMore;
        public String back;
        public String termsAndConditions;
        public String redeemNow;

        public Link( String learnMore, String back, String termsAndConditions, String redeemNow ) {
            this.learnMore = learnMore;
            this.back = back;
            this.termsAndConditions = termsAndConditions;
            this.redeemNow = redeemNow;
        }
    }
}
