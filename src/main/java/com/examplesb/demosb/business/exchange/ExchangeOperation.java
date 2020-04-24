package com.examplesb.demosb.business.exchange;

import io.reactivex.Single;

public interface ExchangeOperation<U> {
  Single updateExchange(U u);

  Single executeExchange(U u);

  Single getExchange(U u);
}
