import React, { useState } from 'react';
import AddPlayer from './AddPlayer';
import PlayerTable from './PlayerTable';

const App = () => {
    const [players, setPlayers] = useState([]);

    const handleAddPlayer = (playerName) => {
        const newPlayer = { name: playerName, chipCount: 0 };
        setPlayers([...players, newPlayer]);
    };

    return (
        <div>
            <h1>Poker Game</h1>
            <AddPlayer onAddPlayer={handleAddPlayer} />
            {players.length > 0 && <PlayerTable players={players} />}
        </div>
    );
};

export default App;