import React, { useState } from 'react';

const AddPlayer = ({ onAddPlayer }) => {
    const [playerName, setPlayerName] = useState('');

    const handleChange = (event) => {
        setPlayerName(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (playerName.trim() !== '') {
            onAddPlayer(playerName);
            setPlayerName('');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Enter player name"
                value={playerName}
                onChange={handleChange}
            />
            <button type="submit">Add Player</button>
        </form>
    );
};

export default AddPlayer;