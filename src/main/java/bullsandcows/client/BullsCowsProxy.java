package bullsandcows.client;

import telran.net.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bullsandcows.implementations.BullsCowsService;
import bullsandcows.implementations.Move;
import bullsandcows.implementations.MoveResult;

public class BullsCowsProxy implements BullsCowsService {
	private final TcpClient tcpClient;

	public BullsCowsProxy(String host, int port) {
		this.tcpClient = new TcpClient(host, port);
	}

	@Override
	public Long createNewGame() {
		Request request = new Request("startGame", "");
		Response response = sendRequest(request);
		Long result = null;
		if (response.responseCode() == ResponseCode.OK) {
			String responseData = response.responseData();
			result = Long.valueOf(responseData.split(":")[1].trim());
		}
		return result;
	}

	@Override
	public List<MoveResult> getResults(long gameId, Move move) {
		Request request = new Request("makeMove", gameId + ";" + move.toString());
		Response response = sendRequest(request);
		List<MoveResult> moveResults = new ArrayList<>();
		if (response.responseCode() == ResponseCode.OK) {
			String[] results = response.responseData().split("\n");
			for (String result : results) {
				String[] parts = result.split(":");
				if (parts.length == 3) {
					String guess = parts[0].trim();
					int bulls = Integer.parseInt(parts[1].trim());
					int cows = Integer.parseInt(parts[2].trim());
					moveResults.add(new MoveResult(guess, bulls, cows));
				}
			}
		}
		return moveResults;
	}

	@Override
	public boolean isGameOver(long gameId) {
		Request request = new Request("checkGameOver", String.valueOf(gameId));
		Response response = sendRequest(request);
		boolean isOver = false;
		if (response.responseCode() == ResponseCode.OK) {
			isOver = Boolean.parseBoolean(response.responseData());
		}
		return isOver;
	}

	private Response sendRequest(Request request) {
		Response response;
		try {
			String responseStr = tcpClient.sendAndReceive(request.toString());
			ResponseCode responseCode = ResponseCode.OK;
			String responseData = responseStr != null ? responseStr : "Communication error";
			response = new Response(responseCode, responseData);
		} catch (RuntimeException e) {
			response = new Response(ResponseCode.ERROR, e.getMessage());
		}
		return response;
	}

}
