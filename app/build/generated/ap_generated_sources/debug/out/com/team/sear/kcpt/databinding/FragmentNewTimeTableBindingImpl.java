package com.team.sear.kcpt.databinding;
import com.team.sear.kcpt.R;
import com.team.sear.kcpt.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNewTimeTableBindingImpl extends FragmentNewTimeTableBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textnodataallweek, 1);
        sViewsWithIds.put(R.id.lessonRecyclerAllWeek, 2);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentNewTimeTableBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private FragmentNewTimeTableBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10000L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.lesson12 == variableId) {
            setLesson12((java.lang.String) variable);
        }
        else if (BR.lesson11 == variableId) {
            setLesson11((java.lang.String) variable);
        }
        else if (BR.lesson10 == variableId) {
            setLesson10((java.lang.String) variable);
        }
        else if (BR.dayName1 == variableId) {
            setDayName1((java.lang.String) variable);
        }
        else if (BR.dayName2 == variableId) {
            setDayName2((java.lang.String) variable);
        }
        else if (BR.todayBtStr == variableId) {
            setTodayBtStr((java.lang.String) variable);
        }
        else if (BR.lesson5 == variableId) {
            setLesson5((java.lang.String) variable);
        }
        else if (BR.lesson4 == variableId) {
            setLesson4((java.lang.String) variable);
        }
        else if (BR.lesson7 == variableId) {
            setLesson7((java.lang.String) variable);
        }
        else if (BR.lesson6 == variableId) {
            setLesson6((java.lang.String) variable);
        }
        else if (BR.lesson9 == variableId) {
            setLesson9((java.lang.String) variable);
        }
        else if (BR.lesson8 == variableId) {
            setLesson8((java.lang.String) variable);
        }
        else if (BR.lesson1 == variableId) {
            setLesson1((java.lang.String) variable);
        }
        else if (BR.lesson3 == variableId) {
            setLesson3((java.lang.String) variable);
        }
        else if (BR.lesson2 == variableId) {
            setLesson2((java.lang.String) variable);
        }
        else if (BR.tomorrowBtStr == variableId) {
            setTomorrowBtStr((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLesson12(@Nullable java.lang.String Lesson12) {
        this.mLesson12 = Lesson12;
    }
    public void setLesson11(@Nullable java.lang.String Lesson11) {
        this.mLesson11 = Lesson11;
    }
    public void setLesson10(@Nullable java.lang.String Lesson10) {
        this.mLesson10 = Lesson10;
    }
    public void setDayName1(@Nullable java.lang.String DayName1) {
        this.mDayName1 = DayName1;
    }
    public void setDayName2(@Nullable java.lang.String DayName2) {
        this.mDayName2 = DayName2;
    }
    public void setTodayBtStr(@Nullable java.lang.String TodayBtStr) {
        this.mTodayBtStr = TodayBtStr;
    }
    public void setLesson5(@Nullable java.lang.String Lesson5) {
        this.mLesson5 = Lesson5;
    }
    public void setLesson4(@Nullable java.lang.String Lesson4) {
        this.mLesson4 = Lesson4;
    }
    public void setLesson7(@Nullable java.lang.String Lesson7) {
        this.mLesson7 = Lesson7;
    }
    public void setLesson6(@Nullable java.lang.String Lesson6) {
        this.mLesson6 = Lesson6;
    }
    public void setLesson9(@Nullable java.lang.String Lesson9) {
        this.mLesson9 = Lesson9;
    }
    public void setLesson8(@Nullable java.lang.String Lesson8) {
        this.mLesson8 = Lesson8;
    }
    public void setLesson1(@Nullable java.lang.String Lesson1) {
        this.mLesson1 = Lesson1;
    }
    public void setLesson3(@Nullable java.lang.String Lesson3) {
        this.mLesson3 = Lesson3;
    }
    public void setLesson2(@Nullable java.lang.String Lesson2) {
        this.mLesson2 = Lesson2;
    }
    public void setTomorrowBtStr(@Nullable java.lang.String TomorrowBtStr) {
        this.mTomorrowBtStr = TomorrowBtStr;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): lesson12
        flag 1 (0x2L): lesson11
        flag 2 (0x3L): lesson10
        flag 3 (0x4L): dayName1
        flag 4 (0x5L): dayName2
        flag 5 (0x6L): todayBtStr
        flag 6 (0x7L): lesson5
        flag 7 (0x8L): lesson4
        flag 8 (0x9L): lesson7
        flag 9 (0xaL): lesson6
        flag 10 (0xbL): lesson9
        flag 11 (0xcL): lesson8
        flag 12 (0xdL): lesson1
        flag 13 (0xeL): lesson3
        flag 14 (0xfL): lesson2
        flag 15 (0x10L): tomorrowBtStr
        flag 16 (0x11L): null
    flag mapping end*/
    //end
}