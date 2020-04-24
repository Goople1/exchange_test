package com.examplesb.demosb.business.monies;

import io.reactivex.Flowable;

public interface MoniesOperation<T> {
  Flowable<T> listMonies();
}
