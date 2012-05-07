package fr.colin.topoguide.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcellableUtils {

   public static <T extends Parcelable> T getParcelable(T object, Creator<T> creator) {
      Parcel parcel = Parcel.obtain();
      object.writeToParcel(parcel, 0);
      parcel.setDataPosition(0);
      return creator.createFromParcel(parcel);
   }
}
