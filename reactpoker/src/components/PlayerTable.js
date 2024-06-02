import React from 'react';
import './PlayerTable.css';
import { getCardImage } from '../utils/loadImages';
import bigBlind from '../../public/images/bigBlind.png';
import smallBlind from '../../public/images/smallBlind.png';
import dealer from '../../public/images/dealer.png';



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
            <div className="playerStatus">
                {player.bigBlind && <img src={bigBlind} className="blind" alt="bigBlind"/>}
                {player.smallBlind && <img src={smallBlind} className="blind" alt = "smallBlind" />}
                {player.dealer && <img src={dealer} className="blind" alt="dealer" />}
            </div>
        </div>
    );
};

export default PlayerTable;