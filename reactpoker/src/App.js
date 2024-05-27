
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

  

    useEffect(() => {
        stompClient.connect({}, () => {
            console.log('Connected to WebSocket');
            stompClient.subscribe('/client/players', (message) => {
                const updatedPlayers = JSON.parse(message.body);
                setPlayers(updatedPlayers);
                
                if (updatedPlayers.length >= 2 && startRoundRef.current) {
                    const dealCardsMessage = { action: 'dealCards' };
                    startRoundRef.current = false; // Nastavenie startRound na false po rozdání karet
                    stompClient.send('/server/dealCards', {}, JSON.stringify(dealCardsMessage));
                 
                }
            });

            // Inicializácia - získanie zoznamu hráčov
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
        setPlayers((prevPlayers) => {
            const newPlayers = [...prevPlayers];
            newPlayers[selectedPlayer - 1] = newPlayer;
            return newPlayers;
        });
        // Odoslanie správy na server
        const sendMessage = (playerName) => {
            stompClient.send('/server/addPlayer', {}, JSON.stringify(playerName));
        };
           // Odoslanie správy na server
        sendMessage(playerName);

        setPlayerPositions((prevPositions) => {
            const newPositions = [...prevPositions];
            newPositions[selectedPlayer - 1] = selectedPlayer;
            return newPositions;
        });

        setSelectedPlayer(null);
    };

    const handlePlayerClick = (playerId) => {
        if (!playerPositions[playerId - 1]) {  
            setSelectedPlayer(playerId);
        }
    
    };

    return (
        <div>
            <PokerTable />
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