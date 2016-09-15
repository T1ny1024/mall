package com.lixiang.smallmall.http;

/**
 * Created by yangjw on 2016/3/16.
 *
 */
public interface IOkCallBack<E> {

    public void onSucess(E resultInfo) ;
    public void onStringSuccess(String resultArray);

}
