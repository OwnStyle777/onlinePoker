import React, { useState } from 'react';
import { Button, TextField, Box, Typography } from '@mui/material';
import './AddPlayer.css';


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
        <div>
        <Box
            component="form"
            onSubmit={handleSubmit}
            id="addPlayer"
        
           
        >
            <Typography variant="h6" id="enterName">Enter Player Name</Typography>
            <TextField
                label="Player Name"
                id="playerNameField"
                variant="outlined"
                value={playerName}
                onChange={handleChange}
            />
            <Button  id="addButton" type="submit" variant="contained" color="primary">
                Add Player
            </Button>
        </Box>
        </div>
    );
};


export default AddPlayer;