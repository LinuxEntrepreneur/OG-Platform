/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.opengamma.sesame.graph.InterfaceNode;
import com.opengamma.sesame.graph.Node;
import com.opengamma.sesame.graph.NodeDecorator;

/**
 * Decorates node in the graph with a proxy.
 * Subclasses should be stateless and thread safe as there is only one instance shared between all proxied objects.
 */
public abstract class ProxyNodeDecorator implements NodeDecorator, InvocationHandlerFactory {

  @Override
  public Node decorateNode(Node node) {
    if (!(node instanceof InterfaceNode)) {
      return node;
    }
    InterfaceNode interfaceNode = (InterfaceNode) node;
    if (decorate(interfaceNode)) {
      return new ProxyNode(node, interfaceNode.getInterfaceType(), this);
    } else {
      return node;
    }
  }

  @Override
  public InvocationHandler create(final Object delegate) {
    return new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ProxyNodeDecorator.this.invoke(proxy, delegate, method, args);
      }
    };
  }

  /**
   * Indicates whether a node should be wrapped in a proxy.
   * @param node The node
   * @return true if the node should be wrapping in a proxy
   */
  protected abstract boolean decorate(InterfaceNode node);

  /**
   * Called when a method on the proxy is invoked.
   * @param proxy The proxy whose method was invoked
   * @param delegate The object being proxied
   * @param method The method that was invoked
   * @param args The method arguments
   * @return The return value of the call
   * @throws Exception If something goes wrong with the underlying call
   */
  protected abstract Object invoke(Object proxy, Object delegate, Method method, Object[] args) throws Exception;

}
