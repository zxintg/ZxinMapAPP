package com.zxin.basemodule.app;


import android.app.Activity;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zxin.basemodule.util.CheckUtil;
import com.zxin.basemodule.views.IMapView;

/**
 * Manager {@link Activity} child fragment.
 *
 *
 */
public class ScenesManager {

    private static FragmentManager mFragmentManager;

    private static BaseScene mMapScene;
    public static void onCreate(FragmentActivity activity) {
        mFragmentManager = activity.getSupportFragmentManager();
        CheckUtil.require(mFragmentManager != null, "Fragment Manager can't be null");
    }

    public synchronized static final void showScene(BaseScene scene) {
        showScene(scene, true);
    }

    public synchronized static final void showScene(BaseScene scene, boolean record) {
        FragmentTransaction action = mFragmentManager.beginTransaction();
       // action.add(R.id.layer_container, scene);
        final BaseScene curScene = getCurrentScene();
        if (curScene != null && curScene.isAdded() && !curScene.isDetached()) {
            action.hide(curScene);
        }
        if (record && scene.getSceneId() != BaseScene.SceneId.SCENE_ID_MAP) {
            action.addToBackStack(null);
        } else {
            mMapScene = scene;
        }
        // Remove the preview map touch listener.
        BaseScene preview = getCurrentScene();
        if (preview != null && preview instanceof IMapView.IMapTouchListener) {
           /* DisplayPresenter.getInstance()
                    .removeMapTouchListener((IMapView.IMapTouchListener)preview);*/
        }

        // add current the preview map touch listener.
        if (scene != null && scene instanceof IMapView.IMapTouchListener) {
//            DisplayPresenter.getInstance().addMapTouchListener((IMapView.IMapTouchListener)scene);
        }

        action.setPrimaryNavigationFragment(scene);
        action.commit();
    }

    public synchronized static final void back() {
        // Remove the current map touch listener.
        BaseScene currentScene = getCurrentScene();
        if (currentScene != null && currentScene instanceof IMapView.IMapTouchListener) {
            /*DisplayPresenter.getInstance()
                    .removeMapTouchListener((IMapView.IMapTouchListener)currentScene);*/
        }
        ZxinRootApp.getApp().getActivity().onBackPressed();
    }

    public static final void doAfterBackPressed() {
        //need showing map.
        if (mFragmentManager.getBackStackEntryCount() <= 0 && mMapScene != null) {
            View view = mMapScene.getView();
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
        // Add current the preview map touch listener.
        BaseScene currentScene = getCurrentScene();
        if (currentScene != null && currentScene instanceof IMapView.IMapTouchListener) {
           /* DisplayPresenter.getInstance()
                    .addMapTouchListener((IMapView.IMapTouchListener)currentScene);*/
        }
    }

    public static FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public static final BaseScene getCurrentScene() {
        BaseScene current = (BaseScene) mFragmentManager.getPrimaryNavigationFragment();
        return current;
    }

    private static boolean isClearScene = false;

    public static final boolean isClearScene() {
        return isClearScene;
    }

    public synchronized static final void clearScene() {
        isClearScene = true;
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        // Add current the preview map touch listener.
        BaseScene currentScene = getCurrentScene();
        if (currentScene != null && currentScene instanceof IMapView.IMapTouchListener) {
            /*DisplayPresenter.getInstance()
                    .addMapTouchListener((IMapView.IMapTouchListener)currentScene);*/
        }
        isClearScene = false;
    }

    public synchronized static void onDestroy() {
        mFragmentManager = null;
    }

    public static final boolean isCurrentScene(BaseScene.SceneId sceneId){
        return getCurrentScene().getSceneId() == sceneId;
    }
}
