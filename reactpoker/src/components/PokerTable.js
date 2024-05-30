import React from 'react';
import '../index.css';
import greenTable from '../../public/images/greenTable.png';
import dealer from '../../public/images/dealer.png';
import blueChip from '../../public/chips/blueChip.png';
import blackChip from '../../public/chips/blackChip.png';
import redChip from '../../public/chips/redChip.png';
import yellowChip from '../../public/chips/yellowChip.png';
import ace from '../../public/cards/A_HEARTS.png';
import cardBack from '../../public/images/cardBack.png';
import { getCardImage } from '../utils/loadImages';
import React from 'react';
import '../index.css';
import greenTable from '../../public/images/greenTable.png';
import cardBack from '../../public/images/cardBack.png';
import { getCardImage } from '../utils/loadImages';

const PokerTable = ({ flopCards }) => {
  // Preverenie, že flopCards je platné pole
  const cardsToShow = flopCards && flopCards.length ? flopCards.slice(0, 3) : [];

  return (
    <div className="background">
      <div id="tableContainer">
        <div id="tableWrapper">
          <img id="table" src={greenTable} alt="Poker Table" />
        </div>
        <div id="floppContainer">
          {cardsToShow.map((card, index) => (
            <img
              key={index}
              className="cards"
              src={getCardImage(`${card}.png`)}
              alt={card}
            />
          ))}
          {new Array(5 - cardsToShow.length).fill(null).map((_, index) => (
            <img
              key={index}
              className="backCards"
              src={cardBack}
              alt="Back of Card"
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default PokerTable;