package com.mhp.coding.challenges.dependency.inquiry;

public class InquiryCreatedEvent {

    private Inquiry inquiry;

    public InquiryCreatedEvent(Inquiry inquiry) {
        this.inquiry = inquiry;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }
}
