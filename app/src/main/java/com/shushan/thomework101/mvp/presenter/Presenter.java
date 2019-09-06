
package com.shushan.thomework101.mvp.presenter;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter<T extends LoadDataView> {

//    void setView(T t);

//    void resume();

//    void pause();


    void onCreate();

    void onDestroy();
}
