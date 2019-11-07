package com.team.sear.kcpt;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.team.sear.kcpt.databinding.FragmentMoreBindingImpl;
import com.team.sear.kcpt.databinding.FragmentNewTimeTableBindingImpl;
import com.team.sear.kcpt.databinding.FragmentWeatherBindingImpl;
import com.team.sear.kcpt.databinding.SplashBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTMORE = 1;

  private static final int LAYOUT_FRAGMENTNEWTIMETABLE = 2;

  private static final int LAYOUT_FRAGMENTWEATHER = 3;

  private static final int LAYOUT_SPLASH = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.team.sear.kcpt.R.layout.fragment_more, LAYOUT_FRAGMENTMORE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.team.sear.kcpt.R.layout.fragment_new_time_table, LAYOUT_FRAGMENTNEWTIMETABLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.team.sear.kcpt.R.layout.fragment_weather, LAYOUT_FRAGMENTWEATHER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.team.sear.kcpt.R.layout.splash, LAYOUT_SPLASH);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTMORE: {
          if ("layout/fragment_more_0".equals(tag)) {
            return new FragmentMoreBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_more is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWTIMETABLE: {
          if ("layout/fragment_new_time_table_0".equals(tag)) {
            return new FragmentNewTimeTableBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_new_time_table is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWEATHER: {
          if ("layout/fragment_weather_0".equals(tag)) {
            return new FragmentWeatherBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_weather is invalid. Received: " + tag);
        }
        case  LAYOUT_SPLASH: {
          if ("layout/splash_0".equals(tag)) {
            return new SplashBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for splash is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(18);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "dayName1");
      sKeys.put(2, "dayName2");
      sKeys.put(3, "lesson1");
      sKeys.put(4, "lesson10");
      sKeys.put(5, "lesson11");
      sKeys.put(6, "lesson12");
      sKeys.put(7, "lesson2");
      sKeys.put(8, "lesson3");
      sKeys.put(9, "lesson4");
      sKeys.put(10, "lesson5");
      sKeys.put(11, "lesson6");
      sKeys.put(12, "lesson7");
      sKeys.put(13, "lesson8");
      sKeys.put(14, "lesson9");
      sKeys.put(15, "todayBtStr");
      sKeys.put(16, "tomorrowBtStr");
      sKeys.put(17, "weatherText");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/fragment_more_0", com.team.sear.kcpt.R.layout.fragment_more);
      sKeys.put("layout/fragment_new_time_table_0", com.team.sear.kcpt.R.layout.fragment_new_time_table);
      sKeys.put("layout/fragment_weather_0", com.team.sear.kcpt.R.layout.fragment_weather);
      sKeys.put("layout/splash_0", com.team.sear.kcpt.R.layout.splash);
    }
  }
}
