package net.devyy.yi.test;

import android.os.Parcel;
import android.os.Parcelable;

public class TestBean implements Parcelable{

    private int i;
    private String str;

    protected TestBean(Parcel in) {
        i = in.readInt();
        str = in.readString();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel in) {
            return new TestBean(in);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };

    @Override
    public int describeContents( ) {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(i);
        parcel.writeString(str);
    }
}
