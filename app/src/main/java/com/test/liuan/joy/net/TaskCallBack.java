package com.test.liuan.joy.net;

public interface TaskCallBack<Result> {
	void onSuccess(Result result);
	
	void onError(Throwable throwable);
}
