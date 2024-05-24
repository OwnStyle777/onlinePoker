import React from 'react';
import '../index.css';
import greenTable from '../../public/images/greenTable.png';
import dealer from '../../public/images/dealer.png';
import blueChip from '../../public/chips/blueChip.png';
import blackChip from '../../public/chips/blackChip.png';
import redChip from '../../public/chips/redChip.png';
import yellowChip from '../../public/chips/yellowChip.png';
import ace from '../../public/images/Ace.png';
import cardBack from '../../public/images/cardBack.png';

const PokerTable = () => {
  return (
    <div className="background">
      <div id="tableContainer">
        <div id="tableWrapper">
          <img id="table" src={greenTable} alt="Poker Table" />
   
        </div>
        <div id="floppContainer">
          <img className="cards" src={ace} alt="Ace" />
          <img className="backCards" src={cardBack} alt="Back of Card" />
          <img className="backCards" src={cardBack} alt="Back of Card" />
          <img className="backCards" src={cardBack} alt="Back of Card" />
          <img className="backCards" src={cardBack} alt="Back of Card" />
        </div>
      </div>
    </div>
  );
};

export default PokerTable;