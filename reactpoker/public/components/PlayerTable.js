import React from 'react';

const PlayerTable = ({ players }) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>Player Name</th>
                    <th>Chip Count</th>
                </tr>
            </thead>
            <tbody>
                {players.map((player, index) => (
                    <tr key={index}>
                        <td>{player.name}</td>
                        <td>{player.chipCount}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};