/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.smith.http.webservice;

import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.CharsetUtil;

import com.google.gson.Gson;
import com.smith.http.webservice.action.ActionTop;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.util.TNUtil;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.*;
import static org.jboss.netty.handler.codec.http.HttpHeaders.*;
import static org.jboss.netty.handler.codec.http.HttpMethod.*;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.*;
import static org.jboss.netty.handler.codec.http.HttpVersion.*;

/**
 * Handles handshakes and messages
 */
public class WebSocketServerHandler extends SimpleChannelUpstreamHandler {
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(WebSocketServerHandler.class);

	private static final String WEBSOCKET_PATH = "/websocket";

	private WebSocketServerHandshaker handshaker;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		// System.out.println("WebSocketServerHandler->messageReceived ");

		Object msg = e.getMessage();
		if (msg instanceof HttpRequest) {
			handleHttpRequest(ctx, (HttpRequest) msg);
		}
		// else if (msg instanceof WebSocketFrame) {
		// handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		// }
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) throws Exception {

		// Allow only GET methods.
		if (req.getMethod() != GET && req.getMethod() != POST) {
			sendHttpResponse(ctx, req, new DefaultHttpResponse(HTTP_1_1, FORBIDDEN));
			return;
		}
		//
		// // Send the demo page and favicon.ico
		// if ("/".equals(req.getUri())) {
		// HttpResponse res = new DefaultHttpResponse(HTTP_1_1, OK);
		//
		// // ChannelBuffer content = WebSocketServerIndexPage
		// // .getContent(getWebSocketLocation(req));
		//
		// ChannelBuffer content = ChannelBuffers.copiedBuffer(
		// TNUtil.fileToContent(TN_Constant.htmlPath + "error.html"),
		// CharsetUtil.UTF_8);
		//
		// res.setHeader(CONTENT_TYPE, "text/html; charset=UTF-8");
		// setContentLength(res, content.readableBytes());
		//
		// res.setContent(content);
		// sendHttpResponse(ctx, req, res);
		// return;
		// }

		if (req.getUri().contains("/")) {
			String[] s = req.getUri().split("/");
			if (s.length == 4) {
				if (TN_Constant.PROJECT_TN.equals(s[1])) {
					System.out.println("request=" + req.getUri());
					System.out.println("req.getContent()=" + URLDecoder.decode(new String(req.getContent().array()), "utf-8"));
					
					Bean_Result result = ActionTop.distributeAction(s);

					if (null != result) {
						ChannelBuffer content = ChannelBuffers.copiedBuffer(TNUtil.gson.toJson(result.getT()),
								CharsetUtil.UTF_8);
						sendHttpResponsePre(ctx, req, content, result.getContent_type());
						return;
					}

				}
			}
		}
		if ("/favicon.ico".equals(req.getUri())) {
			HttpResponse res = new DefaultHttpResponse(HTTP_1_1, NOT_FOUND);
			sendHttpResponse(ctx, req, res);
			return;
		}

		ChannelBuffer content = ChannelBuffers.copiedBuffer(TNUtil.fileToContent(TN_Constant.HTMLPATH + "error.html"),
				CharsetUtil.UTF_8);

		sendHttpResponsePre(ctx, req, content, "text/html");

		//
		// // Handshake
		// WebSocketServerHandshakerFactory wsFactory = new
		// WebSocketServerHandshakerFactory(
		// getWebSocketLocation(req), null, false);
		// handshaker = wsFactory.newHandshaker(req);
		// if (handshaker == null) {
		// wsFactory.sendUnsupportedWebSocketVersionResponse(ctx.getChannel());
		// } else {
		// handshaker.handshake(ctx.getChannel(),
		// req).addListener(WebSocketServerHandshaker.HANDSHAKE_LISTENER);
		// }
	}

	private void sendHttpResponsePre(ChannelHandlerContext ctx, HttpRequest req, ChannelBuffer content,
			String content_type) {

		HttpResponse res = new DefaultHttpResponse(HTTP_1_1, OK);

		res.setHeader(CONTENT_TYPE, content_type + "; charset=UTF-8");
		setContentLength(res, content.readableBytes());
		res.setContent(content);
		sendHttpResponse(ctx, req, res);
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

		// Check for closing frame
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.getChannel(), (CloseWebSocketFrame) frame);
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.getChannel().write(new PongWebSocketFrame(frame.getBinaryData()));
			return;
		}
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass()
					.getName()));
		}

		// Send the uppercase string back.
		String request = ((TextWebSocketFrame) frame).getText();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Channel %s received %s", ctx.getChannel().getId(), request));
		}
		ctx.getChannel().write(new TextWebSocketFrame(request.toUpperCase()));
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, HttpResponse res) {
		// Generate an error page if response status code is not OK (200).
		if (res.getStatus().getCode() != 200) {
			res.setContent(ChannelBuffers.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8));
			setContentLength(res, res.getContent().readableBytes());
		}

		// Send the response and close the connection if necessary.
		ChannelFuture f = ctx.getChannel().write(res);
		// if (!isKeepAlive(req) || res.getStatus().getCode() != 200) {
		f.addListener(ChannelFutureListener.CLOSE);
		// System.out.println("Channel close ");
		// }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}

	private static String getWebSocketLocation(HttpRequest req) {
		return "ws://" + req.getHeader(HOST) + WEBSOCKET_PATH;
	}
}
