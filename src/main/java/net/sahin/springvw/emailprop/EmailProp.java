package net.sahin.springvw.emailprop;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTimeUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Document(collection = "emailProps")
public class EmailProp implements Serializable {

    public String id;
    public long modified = DateTimeUtils.currentTimeMillis();
    public long hash = 0L;
    private boolean deleted = false;
    public String name;
    public String organizationId;
    public String userId;

    public String templateName;
    public String content;
    public String campaignType;
    public String logo;
    public String ctaLink;
    public String inputEmailTitle;
    public String viewerFirstName;
    public String campaignExplanation;
    public String campaignTermsAndConditions;
    public String campaignUnlockActionText;
    public Body body;
    public String font;
    public String backgroundColor;
    public String headerFontColor;
    public String bodyFontColor;
    public boolean isBrandIcon;
    public String footer;
    public String reply;
    public String companyAddress;
    public String brandName;

    public EmailProp() {
    }

    public EmailProp( String id, String name, String organizationId, String userId,
                      String templateName, String content, String campaignType,
                      String logo, String ctaLink, String inputEmailTitle,
                      String viewerFirstName, String campaignExplanation, String campaignTermsAndConditions,
                      String campaignUnlockActionText, Body body, String font, String backgroundColor,
                      String headerFontColor, String bodyFontColor, boolean isBrandIcon, String footer, String reply, String companyAddress,
                      String brandName ) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.userId = userId;
        this.templateName = templateName;
        this.content = content;
        this.campaignType = campaignType;
        this.logo = logo;
        this.ctaLink = ctaLink;
        this.inputEmailTitle = inputEmailTitle;
        this.viewerFirstName = viewerFirstName;
        this.campaignExplanation = campaignExplanation;
        this.campaignTermsAndConditions = campaignTermsAndConditions;
        this.campaignUnlockActionText = campaignUnlockActionText;
        this.body = body;
        this.font = font;
        this.backgroundColor = backgroundColor;
        this.headerFontColor = headerFontColor;
        this.bodyFontColor = bodyFontColor;
        this.isBrandIcon = isBrandIcon;
        this.footer = footer;
        this.reply = reply;
        this.companyAddress = companyAddress;
        this.brandName = brandName;
    }

    @ToString
    @EqualsAndHashCode
    public static class Body implements Serializable {
        public String helloText;
        public String contentText;
        public String campaignFreeformPerkExplanation;
        public String rewardLink;
        public String campaignLinkRewardLink;
        public String drawDate;
        public String competitionDrawDate;
        public String couponCode;
        public String couponValue;
        public boolean isMoreText;
        public String moreText;
        public boolean isShareVideo;
        public String shareVideo;
        public String sharingUrl;
        public boolean isAnyQuery;
        public String anyQuery;
        public String reachEmail;
        public boolean isTermsAndConditions;
        public String termsAndConditions;

        public Body() {

        }

        public Body( String helloText, String contentText, String campaignFreeformPerkExplanation, String rewardLink,
                     String campaignLinkRewardLink, String drawDate, String competitionDrawDate, String couponCode,
                     String couponValue, boolean isMoreText, String moreText, boolean isShareVideo, String shareVideo, String sharingUrl,
                     boolean isAnyQuery, String anyQuery, String reachEmail, boolean isTermsAndConditions,
                     String termsAndConditions ) {
            this.helloText = helloText;
            this.contentText = contentText;
            this.campaignFreeformPerkExplanation = campaignFreeformPerkExplanation;
            this.rewardLink = rewardLink;
            this.campaignLinkRewardLink = campaignLinkRewardLink;
            this.drawDate = drawDate;
            this.competitionDrawDate = competitionDrawDate;
            this.couponCode = couponCode;
            this.couponValue = couponValue;
            this.isMoreText = isMoreText;
            this.moreText = moreText;
            this.isShareVideo = isShareVideo;
            this.shareVideo = shareVideo;
            this.sharingUrl = sharingUrl;
            this.isAnyQuery = isAnyQuery;
            this.anyQuery = anyQuery;
            this.reachEmail = reachEmail;
            this.isTermsAndConditions = isTermsAndConditions;
            this.termsAndConditions = termsAndConditions;
        }
    }
}