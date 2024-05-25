import React, { useState } from 'react';
import AddPlayer from './components/AddPlayer';
import PlayerTable from './components/PlayerTable';
import PokerTable from './components/PokerTable';
import './players.css';

const App = () => {
    const [players, setPlayers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [playerPositions, setPlayerPositions] = useState(Array(9).fill(null));

    const handleAddPlayer = (playerName) => {
        const newPlayer = { name: playerName, chipCount: 20000 };
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