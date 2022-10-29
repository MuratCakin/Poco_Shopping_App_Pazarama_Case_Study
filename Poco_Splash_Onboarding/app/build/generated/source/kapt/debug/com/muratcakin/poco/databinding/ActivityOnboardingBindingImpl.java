package com.muratcakin.poco.databinding;
import com.muratcakin.poco.R;
import com.muratcakin.poco.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOnboardingBindingImpl extends ActivityOnboardingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btnSkip, 2);
        sViewsWithIds.put(R.id.viewPager, 3);
        sViewsWithIds.put(R.id.frameLayoutContainer, 4);
        sViewsWithIds.put(R.id.tabLayout, 5);
        sViewsWithIds.put(R.id.btnPrev, 6);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityOnboardingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ActivityOnboardingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[1]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[2]
            , (android.widget.FrameLayout) bindings[4]
            , (com.google.android.material.tabs.TabLayout) bindings[5]
            , (androidx.viewpager2.widget.ViewPager2) bindings[3]
            );
        this.btnNext.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.isLastPage == variableId) {
            setIsLastPage((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setIsLastPage(@Nullable java.lang.Boolean IsLastPage) {
        this.mIsLastPage = IsLastPage;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.isLastPage);
        super.requestRebind();
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
        boolean androidxDatabindingViewDataBindingSafeUnboxIsLastPage = false;
        java.lang.String isLastPageBtnNextAndroidStringOnBoardingStartButtonBtnNextAndroidStringOnBoardingNextButton = null;
        java.lang.Boolean isLastPage = mIsLastPage;

        if ((dirtyFlags & 0x3L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isLastPage)
                androidxDatabindingViewDataBindingSafeUnboxIsLastPage = androidx.databinding.ViewDataBinding.safeUnbox(isLastPage);
            if((dirtyFlags & 0x3L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxIsLastPage) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(isLastPage) ? @android:string/on_boarding_start_button : @android:string/on_boarding_next_button
                isLastPageBtnNextAndroidStringOnBoardingStartButtonBtnNextAndroidStringOnBoardingNextButton = ((androidxDatabindingViewDataBindingSafeUnboxIsLastPage) ? (btnNext.getResources().getString(R.string.on_boarding_start_button)) : (btnNext.getResources().getString(R.string.on_boarding_next_button)));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnNext, isLastPageBtnNextAndroidStringOnBoardingStartButtonBtnNextAndroidStringOnBoardingNextButton);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): isLastPage
        flag 1 (0x2L): null
        flag 2 (0x3L): androidx.databinding.ViewDataBinding.safeUnbox(isLastPage) ? @android:string/on_boarding_start_button : @android:string/on_boarding_next_button
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(isLastPage) ? @android:string/on_boarding_start_button : @android:string/on_boarding_next_button
    flag mapping end*/
    //end
}