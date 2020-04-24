package com.examplesb.demosb.business.login;

import io.reactivex.Single;

public interface LoginOperation<T, U> {
  Single<U> login(T t);
}
