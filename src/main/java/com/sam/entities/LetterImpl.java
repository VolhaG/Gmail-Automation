package com.sam.entities;

public class LetterImpl implements Letter {

    private String recipient;
    private String topic;
    private String letterContent;

    public LetterImpl(String recipient, String topic, String letterContent) {
        this.recipient = recipient;
        this.topic = topic;
        this.letterContent = letterContent;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setTopic(String recipient) {
        this.topic = recipient;
    }

    public void setLetterContent(String recipient) {
        this.letterContent = recipient;
    }

    @Override
    public String getRecipient() {
        return this.recipient;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }

    @Override
    public String getLetterContent() {
        return this.letterContent;
    }

    @Override
    public String toString() {
        StringBuilder buildLetter = new StringBuilder("Letter with recipient: ").append(this.recipient)
                .append(", topic").append(this.topic).append(", content: ").append(this.getLetterContent());
        return buildLetter.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        Letter letter = (Letter) obj;
        return (this.recipient.equals(letter.getRecipient()) && this.topic.equals(letter.getTopic()) &&
                this.letterContent.equals(letter.getLetterContent()));
    }

}
