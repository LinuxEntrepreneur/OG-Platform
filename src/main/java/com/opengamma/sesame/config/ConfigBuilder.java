/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.security.cashflow.CashFlowSecurity;
import com.opengamma.financial.security.equity.EquitySecurity;
import com.opengamma.sesame.example.CashFlowIdDescription;
import com.opengamma.sesame.example.EquityDescriptionFunction;
import com.opengamma.sesame.example.IdScheme;
import com.opengamma.sesame.example.OutputNames;

/**
 * Mini DSL for building instances of {@link ViewDef} and related classes in code. See the
 * {@link #main} method for an example.
 */
public final class ConfigBuilder {

  private static final Map<Class<?>, FunctionArguments> EMPTY_ARGUMENTS = Collections.emptyMap();
  private static final Map<Class<?>, Class<?>> EMPTY_OVERRIDES = Collections.emptyMap();

  private ConfigBuilder() {
  }

  public static void main(String[] args) {
    ViewDef viewDef =
        viewDef("view name",
                column(OutputNames.DESCRIPTION),
                column(
                    defaultConfig(OutputNames.DESCRIPTION,
                                  config(
                                      implementations(EquityDescriptionFunction.class, CashFlowIdDescription.class),
                                      arguments(
                                          function(IdScheme.class,
                                                   argument("scheme", ExternalSchemes.ACTIVFEED_TICKER)))))),
                column("Bloomberg Ticker",
                       columnOutput(OutputNames.DESCRIPTION),
                       output(EquitySecurity.class,
                              config(
                                  implementations(EquityDescriptionFunction.class, CashFlowIdDescription.class))),
                       output(CashFlowSecurity.class,
                              config(
                                  implementations(EquityDescriptionFunction.class, CashFlowIdDescription.class)))),
                column("ACTIV Symbol",
                       columnOutput(OutputNames.DESCRIPTION),
                       output(EquitySecurity.class,
                              config(
                                  implementations(EquityDescriptionFunction.class, CashFlowIdDescription.class),
                                  arguments(
                                      function(IdScheme.class,
                                               argument("scheme", ExternalSchemes.ACTIVFEED_TICKER))))),
                       output(CashFlowSecurity.class,
                              config(
                                  implementations(EquityDescriptionFunction.class, CashFlowIdDescription.class),
                                  arguments(
                                      function(IdScheme.class,
                                               argument("scheme", ExternalSchemes.ACTIVFEED_TICKER)))))));
    System.out.println(viewDef);
  }

  public static ViewDef viewDef(String name, ViewColumn... columns) {
    return new ViewDef(name, Arrays.asList(columns));
  }

  private static Map<Class<?>, ColumnOutput> createTargetOutputs(TargetOutput... outputs) {
    Map<Class<?>, ColumnOutput> targetOutputs = Maps.newHashMap();
    for (TargetOutput output : outputs) {
      targetOutputs.put(output._inputType, output._output);
    }
    return targetOutputs;
  }

  public static ViewColumn column(String name, TargetOutput... outputs) {
    return new ViewColumn(name, null, createTargetOutputs(outputs));
  }

  public static ViewColumn column(String name, ColumnOutput defaultOutput) {
    return new ViewColumn(name, defaultOutput, Collections.<Class<?>, ColumnOutput>emptyMap());
  }

  public static ViewColumn column(String name) {
    return new ViewColumn(name, new ColumnOutput(name), Collections.<Class<?>, ColumnOutput>emptyMap());
  }

  public static ViewColumn column(ColumnOutput defaultOutput) {
    return new ViewColumn(defaultOutput.getOutputName(), defaultOutput, Collections.<Class<?>, ColumnOutput>emptyMap());
  }

  public static ViewColumn column(String name, ColumnOutput defaultOutput, TargetOutput... targetOutputs) {
    return new ViewColumn(name, defaultOutput, createTargetOutputs(targetOutputs));
  }

  // for the default column output
  public static ColumnOutput columnOutput(String outputName) {
    return new ColumnOutput(outputName);
  }

  // TODO this is really badly named
  // for the default column output
  public static ColumnOutput defaultConfig(String outputName, FunctionConfig config) {
    return new ColumnOutput(outputName, config);
  }

  public static TargetOutput output(String outputName, Class<?> targetType) {
    return new TargetOutput(new ColumnOutput(outputName), targetType);
  }

  public static TargetOutput output(String outputName, Class<?> targetType, FunctionConfig config) {
    return new TargetOutput(new ColumnOutput(outputName, config), targetType);
  }

  // TODO this is a bad name
  // TODO this needs to inherit the output name from the column. not sure that's going to be easy
  // maybe column output needs to allow a null output name
  public static TargetOutput output(Class<?> targetType, FunctionConfig config) {
    return new TargetOutput(new ColumnOutput(null, config), targetType);
  }

  public static FunctionConfig config(Implementations implementations, Arguments arguments) {
    return new SimpleFunctionConfig(implementations._implementations, arguments._arguments);
  }

  public static FunctionConfig config() {
    return new SimpleFunctionConfig(EMPTY_OVERRIDES, EMPTY_ARGUMENTS);
  }


  public static FunctionConfig config(Implementations implementations) {
    return new SimpleFunctionConfig(implementations._implementations, EMPTY_ARGUMENTS);
  }

  public static FunctionConfig config(Arguments arguments) {
    return new SimpleFunctionConfig(EMPTY_OVERRIDES, arguments._arguments);

  }

  public static FunctionConfig config(Arguments arguments, Implementations implementations) {
    return new SimpleFunctionConfig(implementations._implementations, arguments._arguments);
  }

  // TODO this is a misnomer now, there are no default implementation so this doesn't define overrides. implementations?
  public static Implementations implementations(Class<?>... overrides) {
    return new Implementations(overrides);
  }

  public static FnArgs function(Class<?> functionType, Arg... args) {
    return new FnArgs(functionType, args);
  }

  public static Arguments arguments(FnArgs... args) {
    return new Arguments(args);
  }

  public static Arg argument(String name, Object value) {
    return new Arg(name, value);
  }

  // TODO this is a misnomer now, there are no default implementation so this doesn't define overrides. implementations?
  public static class Implementations {

    private final Map<Class<?>, Class<?>> _implementations = Maps.newHashMap();

    private Implementations(Class<?>... implementations) {
      if ((implementations.length % 2) != 0) {
        throw new IllegalArgumentException("Overrides must be specified in pairs of interface implementation");
      }
      for (int i = 0; i < implementations.length; i += 2) {
        _implementations.put(implementations[i], implementations[i + 1]);
      }
    }
  }

  public static class Arguments {

    private final Map<Class<?>, FunctionArguments> _arguments = Maps.newHashMap();

    private Arguments(FnArgs... args) {
      for (FnArgs arg : args) {
        _arguments.put(arg._function, arg._args);
      }
    }
  }

  public static class FnArgs {

    private final Class<?> _function;
    private final FunctionArguments _args;

    private FnArgs(Class<?> function, Arg... args) {
      _function = function;
      Map<String, Object> argVals = Maps.newHashMap();
      for (Arg arg : args) {
        argVals.put(arg._name, arg._value);
      }
      _args = new SimpleFunctionArguments(argVals);
    }
  }

  public static class Arg {

    private final String _name;
    private final Object _value;

    private Arg(String name, Object value) {

      _name = name;
      _value = value;
    }
  }

  public static class TargetOutput {
    private final ColumnOutput _output;
    private final Class<?> _inputType;

    public TargetOutput(ColumnOutput output, Class<?> inputType) {
      _output = output;
      _inputType = inputType;
    }
  }
}
