package com.zxin.basemodule.app;

import android.os.Bundle;
import com.zxin.basemodule.viewmodel.ActivityViewModel;
import com.zxin.basemodule.viewmodel.AppViewModel;
import com.zxin.basemodule.viewmodel.SceneViewModel;

/**
 * The main element of the architecture.
 *
 *
 * BasePresenter is a main element of the architecture.
 *
 * Presenter will handle all the logic,
 * BasePresenter will manager all the application data and output to view model.
 * BaseScene will listener the view model and update the ui.
 *
 * Author: alex.liu
 */
public abstract class BasePresenter {

    /**
     *  Store the intent result which to give its caller
     *  you need to use {@link #setResult(IIntent)} to set this data and finish current present.
     *  back to preview present scene.
     */
    private IIntent mResult;

    /**
     * The scene which associate with the present.
     */
    private BaseScene mScene;

    /**
     * Default scene view model associate with scene and presenter.
     */
    protected SceneViewModel mModel;

    /**
     * started params.
     */
    protected final IIntent mIntent;

    /**
     * The caller which start this presenter.
     * if the start intent {@link #mIntent}'s action is
     * {@link IIntent#ACTION_GET_RESULT}, this presenter must use {@link #setResult(IIntent)} to
     * give the data to {@link #mParent}
     */
    private BasePresenter mParent;

    /**
     * Is or not attach a scene with this presenter
     */
    private boolean isAttachScene = false;

    public BasePresenter(IIntent intent) {
        this(intent, null);
    }

    public BasePresenter(IIntent intent, BasePresenter parent) {
        mIntent = intent;
        mParent = parent;
        onCreate(intent);
    }

    /**
     *
     * @param intent
     */
    protected void onCreate(IIntent intent) {

    }

    /**
     *
     * @param scene
     */
    public final void attachScene(BaseScene scene) {

        require(mScene == null, "Has been attach present. can't attach again");
        mScene = scene;
        mModel = scene.getSceneViewModel();
        isAttachScene = true;
        onAttachScene();
    }

    /**
     * Called after scene attach.
     */
    protected void onAttachScene() {}

    public final void detachScene(BaseScene scene) {

        onDetachScene();
        mScene = null;
        isAttachScene = false;
    }

    /**
     * Is or not attach associate scene.
     * @return true if has start a scene.
     */
    protected boolean isAttachScene() {
        return isAttachScene;
    }

    /**
     * Caller after detach scene.
     */
    protected void onDetachScene() {}

    /**
     * show the associate scene.
     *
     * after scene ready to show will called {@link #onAttachScene()}
     * @param bundle
     */
    protected abstract void showScene(Bundle bundle);


    /**
     * The scene view model.
     *
     * @return
     */
    public final SceneViewModel getSceneViewModel() {
        require(mScene != null, "The scene not ready" +
                ", please call the method after attach scene.");
        return mModel;
    }

    /**
     * Get a scene view model.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public final  <T extends SceneViewModel> T getSceneViewModel(Class<T> clazz) {
        return mScene.getSceneViewModel(clazz);
    }

    /**
     * Get app view model. the life cycle is application life cycle.
     * @param clazz
     * @param <T>
     * @return
     */
    public final <T extends AppViewModel> T getAppViewModel(Class<T> clazz) {
        return ZxinRootApp.getApp().getAppViewModel(clazz);
    }


    /**
     * Start present for to get result from other present.
     *
     * get result via [onPresentResult]
     *
     * @param intent Intent
     * @param sceneId [BaseScene.SCENE_ID_MAP]
     * @param requestCode request code
     */
    protected final void startPresentForResult(IIntent intent, BaseScene.SceneId sceneId, int requestCode) {

        IIntent temp = intent;
        if (temp == null) {
            temp = new BaseIntent(IIntent.ACTION_GET_RESULT);
        }
        temp.setAction(IIntent.ACTION_GET_RESULT);
        temp.putInt(IIntent.EXTRA_REQUEST_CODE, requestCode);
        //ScenesFactory.createPresentBySceneId(sceneId, temp, this);
    }

    /**
     * After user call [startPresentForResult]
     *
     * @param data to get result
     * @param resultCode [IIntent.RESULT_OK] or [IIntent.RESULT_ERROR]
     * @param requestCode the value user call from
     *
     */
    protected void onPresentResult(IIntent data, int resultCode, int requestCode) {

    }

    /**
     * Set the result data and give it to its caller {@link #mParent}
     * @param data
     */
    public final void setResult(IIntent data) {
        require(data.hasExtra(IIntent.EXTRA_RESULT_CODE),
                "setResult data must has a extra $IIntent.EXTRA_RESULT_CODE");
        require(mIntent!= null  && IIntent.ACTION_GET_RESULT.equals(mIntent.getAction()),
                "Only start action is IIntent.ACTION_GET_RESULT can't setResult: " + mIntent.getAction());
        mResult = data;
        finish();
    }

    /**
     * The util method, require or check util.
     * @param state
     * @param message
     */
    protected final void require(boolean state, String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Finish this presenter, and destroy associate scene if exist.
     *
     */
    protected final void finish() {
        if (mScene != null) {
            mScene.finish();
        }
        if (mIntent != null && mParent != null && IIntent.ACTION_GET_RESULT == mIntent.getAction()) {
            if (mResult == null) {
                mResult = new BaseIntent();
            }
            mResult.setRequestIntent(mIntent);
            int requestCode = mIntent.getInt(IIntent.EXTRA_REQUEST_CODE, -1);
            int resultCode = mResult.getInt(IIntent.EXTRA_RESULT_CODE, IIntent.RESULT_ERROR);
            mResult.putInt(IIntent.EXTRA_RESULT_CODE, resultCode);
            mResult.putInt(IIntent.EXTRA_REQUEST_CODE, requestCode);
            mParent.onPresentResult(mResult, resultCode, requestCode);
        }
    }

    /**
     * get the string by string res id.
     * @param resId
     * @return
     */
    protected String getString(int resId) {
        return ZxinRootApp.getApp().getString(resId);
    }

    /**
     *
     * Caller after back pressed.
     *
     * @return
     */
    public boolean onBackPressed() {
        return false;
    }

    public void onPresenterPause(){

    }
}
