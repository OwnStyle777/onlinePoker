import React, { useState } from 'react';
import AddPlayer from './components/AddPlayer';
import PlayerTable from './components/PlayerTable';
import PokerTable from './components/PokerTable';
import './players.css'; 

const App = () => {
    const [players, setPlayers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState(null);

    const handleAddPlayer = (playerName) => {
        const newPlayer = { name: playerName, chipCount: 0 };
        setPlayers([...players, newPlayer]);
        setSelectedPlayer(null); // Skryť komponent AddPlayer po pridaní hráča
    };

    const handlePlayerClick = (playerId) => {
        setSelectedPlayer(playerId);
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
                    </div>
                ))}
         
            {players.length > 0 && <PlayerTable players={players} />}
        </div>
    );
};

export default App;