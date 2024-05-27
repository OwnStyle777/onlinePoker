import React from 'react';
import './PlayerTable.css';
import { getCardImage } from '../utils/loadImages';



const PlayerTable = ({ player }) => {
    return (
        <div id="playerTable">
            <h3>{player.name}</h3>
            <p>Chips: {player.totalChips}</p>
            <div className="cards1">
                {player.cards && player.cards.map((card, index) => (
                 <img  id="card1" key={index} src={getCardImage(`${card}.png`)} alt={card} />
                ))}
            </div>
        </div>
    );
};

export default PlayerTable;