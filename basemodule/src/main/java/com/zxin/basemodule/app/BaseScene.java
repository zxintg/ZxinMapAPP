package com.zxin.basemodule.app;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zxin.basemodule.viewmodel.ActivityViewModel;
import com.zxin.basemodule.viewmodel.AppViewModel;
import com.zxin.basemodule.viewmodel.SceneViewModel;

public abstract class BaseScene extends Fragment {

    public enum SceneId {
        SCENE_ID_MAP,
        SCENE_ID_SEARCH,
        SCENE_ID_ROUTE,
        SCENE_ID_PINNING,
        SCENE_ID_GUIDE,
        SCENE_ID_SEARCH_RESULT,
        SCENE_ID_SETTING,
        SCENE_ID_ABOUT_NAVI,
        SCENE_ID_CHARGING
    }

    protected abstract SceneId getSceneId();

    protected BasePresenter mPresenter;
    private SceneViewModel mSceneViewModel;

    public void setPresenter(BasePresenter presenter) {
        require(mPresenter == null, "${tag.tag} has been set a present, can't set another, " +
                "please do not call setPresent yourself");
        mPresenter = presenter;
    }

    protected final void require(boolean state, String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }


    public SceneViewModel getSceneViewModel() {
        return mSceneViewModel;
    }

    private ViewModelProvider mViewModelProvider;

    public final  <T extends SceneViewModel> T getSceneViewModel(Class<T> clazz) {
        if (mViewModelProvider == null) {
            ViewModelProvider.AndroidViewModelFactory factory
                    = ViewModelProvider.AndroidViewModelFactory.getInstance(ZxinRootApp.getApp());
            mViewModelProvider = new ViewModelProvider(getViewModelStore(), factory);
        }
        return mViewModelProvider.get(clazz);
    }

    public final <T extends AppViewModel> T getAppViewModel(Class<T> clazz) {
        return ZxinRootApp.getApp().getAppViewModel(clazz);
    }


    @Override
    public void onAttach(Context context) {
        onAttachPresent(mPresenter);
        super.onAttach(context);
    }

    protected void onAttachPresent(BasePresenter presenter) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSceneViewModel = onCreateSceneViewModel();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            onShow();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.onScenePause();
        if (mPresenter == null){
            return;
        }
        mPresenter.onPresenterPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * Receive map touch down event.
     * if {@link BaseScene} impl {@link com.nio.map.view.IMapView.IMapTouchListener}
     * this method can receive event. otherwise, this method not work.
     *
     * @param event
     */
    public void onMapDown(MotionEvent event) {

    }


    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onSetupView(inflater, container, savedInstanceState);
        //((BaseFrameLayout) view).setSceneId(getSceneId());
        if (mPresenter != null) {
            mPresenter.attachScene(this);
        }
        return view;
    }


    public abstract View onSetupView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    protected abstract SceneViewModel onCreateSceneViewModel();

    @Override
    public final void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        boolean isClearStack = ScenesManager.isClearScene();
        if (!isClearStack) {
            if (hidden) {
                onHide();
            } else {
                onShow();
            }
        }
    }

    protected void onShow() {

    }

    protected void onHide() {

    }

    public final void finish() {
        ScenesManager.back();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachScene(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onDetachPresent();
    }


    protected void onDetachPresent() {

    }


    public final boolean onBackPressed() {
        return mPresenter.onBackPressed();
    }


    protected boolean isShowMainWidget() {
        SceneId sceneId = getSceneId();
        if (sceneId == null) {
            return false;
        }
        return sceneId == SceneId.SCENE_ID_MAP || sceneId == SceneId.SCENE_ID_GUIDE;
    }

    /**
     * Change the full screen or home screen config.
     *
     * @param config see {{@link #CONFIG_FULL_SCREEN}} and {@link #CONFIG_HOME_SCREEN}
     */
    public void changConfig(int config) {    }

    public void onHomePressed() {
        //MapAutoDownload.getInstance().onHomePressed();
    }

    public void onScenePause(){

    }

}
