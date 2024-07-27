package bullsandcows.server;

import telran.net.*;
import bullsandcows.implementations.*;

import java.util.List;

public class BullsCowsServerHandler implements Protocol {
    private final BullsCowsService bullsCowsService;

    public BullsCowsServerHandler(BullsCowsService bullsCowsService) {
        this.bullsCowsService = bullsCowsService;
    }

    @Override
    public Response getResponse(Request request) {
        switch (request.requestType()) {
            case "startGame":
                return handleStartGame();
            case "makeMove":
                Move move = parseMove(request.requestData());
                return handleMakeMove(move);
            case "isGameOver":
                Long gameId = parseGameId(request.requestData());
                return handleIsGameOver(gameId);
            default:
                return new Response(ResponseCode.WRONG_REQUEST, "Unknown request type");
        }
    }

    private Response handleStartGame() {
        Long gameId = bullsCowsService.createNewGame();
        return new Response(ResponseCode.OK, gameId.toString());
    }

    private Response handleMakeMove(Move move) {
        List<MoveResult> results = bullsCowsService.getResults(move.gameId(), move);
        return new Response(ResponseCode.OK, results.toString());
    }

    private Response handleIsGameOver(Long gameId) {
        boolean isOver = bullsCowsService.isGameOver(gameId);
        return new Response(ResponseCode.OK, Boolean.toString(isOver));
    }

    private Move parseMove(String data) {
        String[] parts = data.split(";");
        Long gameId = Long.parseLong(parts[0].trim());
        String moveData = parts[1].trim();
        return new Move(gameId, moveData); 
    }

    private Long parseGameId(String data) {
        return Long.parseLong(data);
    }
}
