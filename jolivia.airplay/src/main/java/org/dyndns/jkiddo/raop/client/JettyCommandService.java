package org.dyndns.jkiddo.raop.client;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dyndns.jkiddo.raop.client.command.PlayCommand;
import org.dyndns.jkiddo.raop.client.command.RateCommand;
import org.dyndns.jkiddo.raop.client.command.ScrubCommand;
import org.dyndns.jkiddo.raop.client.command.StopCommand;
import org.dyndns.jkiddo.raop.client.model.Device;
import org.dyndns.jkiddo.raop.client.model.DeviceConnection;
import org.dyndns.jkiddo.raop.client.model.DeviceResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;

/**
 * Copyright (c) 2011 Carson McDonald Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

public class JettyCommandService extends AbstractHandler
{
	private static final Logger LOGGER = Logger.getLogger(JettyCommandService.class.getName());

	private static Map<String, Device> deviceMap = new HashMap<>();

	public static void addDevice(Device device)
	{
		if(!deviceMap.containsKey(device.getId()))
		{
			deviceMap.put(device.getId(), device);
		}
	}

	public static void removeDevice(String deviceId)
	{
		if(deviceMap.containsKey(deviceId))
		{
			Device removedDevice = deviceMap.remove(deviceId);
			DeviceConnection deviceConnection = DeviceConnectionService.getConnection(removedDevice);
			if(deviceConnection != null)
			{
				deviceConnection.close();
			}
		}
	}

	private void sendOKResponse(Request baseRequest, HttpServletResponse response) throws IOException
	{
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("responseCode", "OK");
		response.getWriter().println(new Gson().toJson(responseMap));
	}

	private void sendErrorResponse(int statusCode, String errorMessage, Request baseRequest, HttpServletResponse response) throws IOException
	{
		response.setStatus(statusCode);
		baseRequest.setHandled(true);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("responseCode", "ERROR");
		responseMap.put("errorMessage", errorMessage);
		response.getWriter().println(new Gson().toJson(responseMap));
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		if("/ci".equals(request.getRequestURI()))
		{
			try
			{
				handleCommandRequest(baseRequest, request, response);
			}
			catch(RuntimeException e)
			{
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private void handleCommandRequest(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");

		switch(Integer.parseInt(request.getParameter("t")))
		{
			case 1: // List devices
			{
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);

				List<Map<String, Object>> deviceList = new ArrayList<>();
				for(Device device : deviceMap.values())
				{
					Map<String, Object> responseMap = new HashMap<>();
					responseMap.put("deviceId", device.getId());
					responseMap.put("deviceName", device.getName());
					deviceList.add(responseMap);
				}

				Map<String, Object> responseMap = new HashMap<>();
				responseMap.put("responseCode", "OK");
				responseMap.put("devices", deviceList);
				response.getWriter().println(new Gson().toJson(responseMap));
			}
				break;
			case 2: // Start stream
			{
				String deviceId = request.getParameter("d");
				String streamURL = request.getParameter("s");

				Device device = deviceMap.get(deviceId);
				if(device == null)
				{
					LOGGER.log(Level.SEVERE, "Device ID is not valid: " + deviceId);
					sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Device ID is not valid: " + deviceId, baseRequest, response);
				}
				else
				{
					DeviceConnection deviceConnection = DeviceConnectionService.getConnection(device);

					DeviceResponse deviceResponse = deviceConnection.sendCommand(new PlayCommand(URLDecoder.decode(streamURL, "utf-8"), 0.0));

					if(deviceResponse.getResponseCode() == 200)
					{
						sendOKResponse(baseRequest, response);
					}
					else
					{
						sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, deviceResponse.getResponseMessage(), baseRequest, response);
					}
				}
			}
				break;
			case 3: // Stop stream
			{
				String deviceId = request.getParameter("d");

				Device device = deviceMap.get(deviceId);
				DeviceConnection deviceConnection = DeviceConnectionService.getConnection(device);

				DeviceResponse deviceResponse = deviceConnection.sendCommand(new StopCommand());

				if(deviceResponse.getResponseCode() == 200)
				{
					sendOKResponse(baseRequest, response);
				}
				else
				{
					sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, deviceResponse.getResponseMessage(), baseRequest, response);
				}
			}
				break;
			case 4: // Get current content play state
			{
				String deviceId = request.getParameter("d");

				Device device = deviceMap.get(deviceId);
				DeviceConnection deviceConnection = DeviceConnectionService.getConnection(device);

				DeviceResponse deviceResponse = deviceConnection.sendCommand(new ScrubCommand());

				if(deviceResponse.getResponseCode() == 200)
				{
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					Map<String, Object> responseMap = new HashMap<>();
					responseMap.put("responseCode", "OK");
					responseMap.put("duration", deviceResponse.getContentParameterMap().get("duration"));
					responseMap.put("position", deviceResponse.getContentParameterMap().get("position"));
					response.getWriter().println(new Gson().toJson(responseMap));
				}
				else
				{
					LOGGER.log(Level.INFO, "Error response: code=" + deviceResponse.getResponseCode() + " message=" + deviceResponse.getResponseMessage());
					sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, deviceResponse.getResponseMessage(), baseRequest, response);
				}
			}
				break;
			case 5: // Change the rate of playback
			{
				String deviceId = request.getParameter("d");
				double rate = 0.0;
				boolean rateParsed = false;
				try
				{
					rate = Double.parseDouble(request.getParameter("r"));
					rateParsed = true;
				}
				catch(NumberFormatException e)
				{
					LOGGER.log(Level.INFO, "Could not parse rate parameter: " + request.getParameter("r"));
					sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Rate is not valid.", baseRequest, response);
				}

				if(rateParsed)
				{
					Device device = deviceMap.get(deviceId);
					DeviceConnection deviceConnection = DeviceConnectionService.getConnection(device);

					DeviceResponse deviceResponse = deviceConnection.sendCommand(new RateCommand(rate));

					if(deviceResponse.getResponseCode() == 200)
					{
						sendOKResponse(baseRequest, response);
					}
					else
					{
						sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, deviceResponse.getResponseMessage(), baseRequest, response);
					}
				}
			}
				break;
			case 6: // Scrub to the given location in the stream
			{
				String deviceId = request.getParameter("d");

				boolean scrubToParsed = false;
				double scrubTo = 0.0;
				try
				{
					scrubTo = Double.parseDouble(request.getParameter("p"));
					scrubToParsed = true;
				}
				catch(NumberFormatException e)
				{
					LOGGER.log(Level.INFO, "Could not parse position parameter: " + request.getParameter("p"));
					sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Position is not valid.", baseRequest, response);
				}

				if(scrubToParsed)
				{
					Device device = deviceMap.get(deviceId);
					DeviceConnection deviceConnection = DeviceConnectionService.getConnection(device);

					DeviceResponse deviceResponse = deviceConnection.sendCommand(new ScrubCommand(scrubTo));

					if(deviceResponse.getResponseCode() == 200)
					{
						response.setStatus(HttpServletResponse.SC_OK);
						baseRequest.setHandled(true);
						Map<String, Object> responseMap = new HashMap<>();
						responseMap.put("responseCode", "OK");
						responseMap.put("duration", deviceResponse.getContentParameterMap().get("duration"));
						responseMap.put("position", deviceResponse.getContentParameterMap().get("position"));
						response.getWriter().println(new Gson().toJson(responseMap));
					}
					else
					{
						LOGGER.log(Level.INFO, "Error response: code=" + deviceResponse.getResponseCode() + " message=" + deviceResponse.getResponseMessage());
						sendErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, deviceResponse.getResponseMessage(), baseRequest, response);
					}
				}
			}
				break;
			default:
			{
				LOGGER.info("Command not understood: " + request.getParameter("t"));
				sendErrorResponse(HttpServletResponse.SC_NOT_ACCEPTABLE, "Command not understood.", baseRequest, response);
			}
		}
	}
}
