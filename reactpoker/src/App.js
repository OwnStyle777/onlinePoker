
import React, { useEffect, useState, useRef } from 'react';
import stompClient from './webSocket';
import AddPlayer from './components/AddPlayer';
import PlayerTable from './components/PlayerTable';
import PokerTable from './components/PokerTable';
import './players.css';



const App = () => {
    const [players, setPlayers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [playerPositions, setPlayerPositions] = useState(Array(9).fill(null));
    const startRoundRef = useRef(true); // Použitie useRef na udržanie stavu
    const [flopCards, setFlopCards] = useState([]);
    const [turnCard, setTurnCard] = useState(null);
    const [riverCard, setRiverCard] = useState(null);

  

    useEffect(() => {
        stompClient.connect({}, () => {
            console.log('Connected to WebSocket');

            stompClient.subscribe('/client/cards', (message) => {
                const flop = JSON.parse(message.body);
                console.log('Received flop:', flop); 
                setFlopCards(flop);
              });

              stompClient.subscribe('/client/turn', (message) => {
                const turn = JSON.parse(message.body);
                console.log('Received turn:', turn); 
                setTurnCard(turn);
              });

            stompClient.subscribe('/client/players', (message) => {
                const updatedPlayers = JSON.parse(message.body);
                setPlayers(updatedPlayers);
                
                const realPlayers = updatedPlayers.filter(player => player !== null);
                if (realPlayers.length >= 2 && startRoundRef.current) {
                    const dealCardsMessage = { action: 'dealCards' };
                    const dealTheFlopMessage = { action: 'dealTheFlop' };
                    const dealTheTurnMessage = {action: 'dealTheTurn'};
                  
                    stompClient.send('/server/dealCards', {}, JSON.stringify(dealCardsMessage));
                    stompClient.send('/server/dealTheFlop', {}, JSON.stringify(dealTheFlopMessage));
                    setTimeout(() => {
                        stompClient.send('/server/dealTheTurn', {}, JSON.stringify(dealTheTurnMessage));
                      }, 1000); // one second between flop and turn
                    

                    startRoundRef.current = false; //set startRoundRef tu false after deal the cards
                 
                }
            });

            //inicialization list of player 
            stompClient.send('/server/getPlayers', {}, {});
        });

        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, []);



    
    const handleAddPlayer = (playerName) => {
        const newPlayer = { pName: playerName, chipCount: 20000 };

        // Check if selectedPlayer is valid and within range
        if (selectedPlayer > 0 && selectedPlayer <= 9) {
            setPlayers((prevPlayers) => {
                const newPlayers = [...prevPlayers];
                newPlayers[selectedPlayer - 1] = newPlayer;
                return newPlayers;
            });

            setPlayerPositions((prevPositions) => {
                const newPositions = [...prevPositions];
                newPositions[selectedPlayer - 1] = selectedPlayer;
                return newPositions;
            });

            //send message to server
            const playerData = { name: playerName, position: selectedPlayer - 1 };
        stompClient.send('/server/addPlayer', {}, JSON.stringify(playerData));

            setSelectedPlayer(null);
        } else {
            console.warn('Invalid player position selected.');
        }
    };

    const handlePlayerClick = (playerId) => {
        if (!playerPositions[playerId - 1]) {  
            setSelectedPlayer(playerId);
        }
    
    };

    return (
        <div>
            <PokerTable flopCards = {flopCards} turnCard = {turnCard} />
            {[...Array(9)].map((_, index) => (
                <div
                    key={index}
                    id={`player${index + 1}`}
                    className="players"
                    onClick={() => handlePlayerClick(index + 1)}
                >
                    <div className="plusIcon">+</div>
                    {selectedPlayer === index + 1 && (
                        <AddPlayer onAddPlayer={handleAddPlayer} />
                    )}
                    {playerPositions[index] && players[index] && (
                        <PlayerTable player={players[index]} />
                    )}
                </div>
            ))}
        </div>
    );
};

export default App;