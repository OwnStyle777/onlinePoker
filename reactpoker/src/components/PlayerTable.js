import React from 'react';
import './PlayerTable.css';

const PlayerTable = ({ player }) => {
    return (
        <table id="playerTable">
        <thead>
            <tr>
                <th>Player Name</th>
                <th>Chip Count</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>{player.name}</td>
                <td>{player.chipCount}</td>
            </tr>
        </tbody>
    </table>
    );
};

export default PlayerTable;