/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.forex.definition;

import javax.time.calendar.ZonedDateTime;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.forex.calculator.ForexConverter;
import com.opengamma.financial.forex.calculator.ForexDefinitionVisitor;
import com.opengamma.financial.forex.calculator.ForexDerivative;
import com.opengamma.financial.forex.derivative.Forex;
import com.opengamma.financial.forex.derivative.ForexOptionVanilla;

/**
 * Class describing a vanilla foreign exchange European option. When the option is a call, the option holder has the right to enter into the Forex transaction; 
 * if the option is a put, the option holder has the right to enter into a Forex transaction equal to the underlying but with opposite signs.
 * A Call on a Forex EUR 1.00 / USD -1.41 is thus the right to call 1.00 EUR and put 1.41 USD. A put on a Forex EUR -1.00 / USD 1.41 is the right to 
 * exchange -(-1.00) EUR = 1.00 EUR and -1.41 EUR; it is thus also the right to call 1.00 EUR and put 1.41 USD. A put on a Forex  USD 1.41 / EUR -1.00 is 
 * also the right to call 1.00 EUR and put 1.41 USD.
 */
public class ForexOptionVanillaDefinition implements ForexConverter<ForexDerivative> {

  /**
   * The underlying Forex transaction (the one entered into in case of exercise).
   */
  private final ForexDefinition _underlyingForex;
  //TODO: Add the strike?
  /**
   * The expiration date (and time) of the option.
   */
  private final ZonedDateTime _expirationDate;
  /**
   * The call (true) / put (false) flag.
   */
  private final boolean _isCall;

  /**
   * Constructor from the details.
   * @param forex The underlying Forex transaction.
   * @param expirationDate The expiration date (and time) of the option.
   * @param isCall The call (true) / put (false) flag.
   */
  public ForexOptionVanillaDefinition(ForexDefinition forex, ZonedDateTime expirationDate, boolean isCall) {
    Validate.notNull(forex, "Underlying forex");
    Validate.notNull(expirationDate, "Expiration date");
    Validate.isTrue(!expirationDate.isAfter(forex.getExchangeDate()), "Expiration should be before payment.");
    this._underlyingForex = forex;
    this._expirationDate = expirationDate;
    this._isCall = isCall;
  }

  /**
   * Gets the underlying Forex transaction.
   * @return The underlying Forex transaction.
   */
  public ForexDefinition getUnderlyingForex() {
    return _underlyingForex;
  }

  /**
   * Gets the expiration date (and time) of the option.
   * @return The expiration date.
   */
  public ZonedDateTime getExpirationDate() {
    return _expirationDate;
  }

  /**
   * Gets the call (true) / put (false) flag.
   * @return The call / put flag.
   */
  public boolean isCall() {
    return _isCall;
  }

  @Override
  public ForexOptionVanilla toDerivative(ZonedDateTime date, String... yieldCurveNames) {
    final Forex fx = _underlyingForex.toDerivative(date, yieldCurveNames);
    final DayCount actAct = DayCountFactory.INSTANCE.getDayCount("Actual/Actual ISDA");
    final double expirationTime = actAct.getDayCountFraction(date, _expirationDate);
    return new ForexOptionVanilla(fx, expirationTime, _isCall);
  }

  @Override
  public <U, V> V accept(ForexDefinitionVisitor<U, V> visitor, U data) {
    return visitor.visitForexOptionVanillaDefinition(this, data);
  }

  @Override
  public <V> V accept(ForexDefinitionVisitor<?, V> visitor) {
    return visitor.visitForexOptionVanillaDefinition(this);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _expirationDate.hashCode();
    result = prime * result + (_isCall ? 1231 : 1237);
    result = prime * result + _underlyingForex.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ForexOptionVanillaDefinition other = (ForexOptionVanillaDefinition) obj;
    if (!ObjectUtils.equals(_expirationDate, other._expirationDate)) {
      return false;
    }
    if (_isCall != other._isCall) {
      return false;
    }
    if (!ObjectUtils.equals(_underlyingForex, other._underlyingForex)) {
      return false;
    }
    return true;
  }

}
