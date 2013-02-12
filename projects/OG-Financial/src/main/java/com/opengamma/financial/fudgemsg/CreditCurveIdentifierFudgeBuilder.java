/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.financial.credit.CreditCurveIdentifier;
import com.opengamma.util.money.Currency;

/**
 * Builder for converting {@link CreditCurveIdentifier} instances to / from Fudge messages.
 */
@FudgeBuilderFor(CreditCurveIdentifier.class)
public class CreditCurveIdentifierFudgeBuilder implements FudgeBuilder<CreditCurveIdentifier> {
  private static final String RED_CODE = "redCode";
  private static final String CURRENCY = "currency";
  private static final String TERM = "term";
  private static final String SENIORITY = "seniority";
  private static final String RESTRUCTURING_CLAUSE = "restructuringClause";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final CreditCurveIdentifier object) {
    final MutableFudgeMsg message = serializer.newMessage();
    FudgeSerializer.addClassHeader(message, CreditCurveIdentifier.class);
    message.add(RED_CODE, object.getRedCode());
    message.add(CURRENCY, object.getCurrency());
    message.add(TERM, object.getTerm());
    message.add(SENIORITY, object.getSeniority());
    message.add(RESTRUCTURING_CLAUSE, object.getRestructuringClause());
    return message;
  }

  @Override
  public CreditCurveIdentifier buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final String redCode = message.getString(RED_CODE);
    final Currency currency = message.getValue(Currency.class, CURRENCY);
    final String term = message.getString(TERM);
    final String seniority = message.getString(SENIORITY);
    final String restructuringClause = message.getString(RESTRUCTURING_CLAUSE);
    return CreditCurveIdentifier.of(redCode, currency, term, seniority, restructuringClause);
  }

}
