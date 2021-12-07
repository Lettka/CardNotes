package com.example.cardnotes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.IdRes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class CardNote implements Parcelable {
    private String id;
    private String titleNotes;
    private String contextNotes;
    private String date;
    @IdRes
    private int image;
    private boolean isLike;

    public CardNote(String titleNotes, String contextNotes, String date, int image, boolean isLike) {
        this.titleNotes = titleNotes;
        this.contextNotes = contextNotes;
        this.date = date;
        this.image = image;
        this.isLike = isLike;
        this.id = UUID.randomUUID().toString();
    }

    public CardNote(String titleNotes, String contextNotes, String date, int image) {
        this(titleNotes, contextNotes, date, image, false);
    }

    public CardNote(String titleNotes, String contextNotes, int image) {
        this(titleNotes, contextNotes, getCurrentDate(), image, false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CardNote() {
        this("", "", getCurrentDate(), 0, false);
    }

    protected CardNote(Parcel in) {
        titleNotes = in.readString();
        contextNotes = in.readString();
        date = in.readString();
        image = in.readInt();
        isLike = in.readByte() != 0;
    }

    public static final Creator<CardNote> CREATOR = new Creator<CardNote>() {
        @Override
        public CardNote createFromParcel(Parcel in) {
            return new CardNote(in);
        }

        @Override
        public CardNote[] newArray(int size) {
            return new CardNote[size];
        }
    };

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public String getTitleNotes() {
        return titleNotes;
    }

    public void setTitleNotes(String titleNotes) {
        this.titleNotes = titleNotes;
    }

    public String getContextNotes() {
        return contextNotes;
    }

    public void setContextNotes(String contextNotes) {
        this.contextNotes = contextNotes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titleNotes);
        parcel.writeString(contextNotes);
        parcel.writeString(date);
        parcel.writeInt(image);
        parcel.writeByte((byte) (isLike ? 1 : 0));
    }
}
