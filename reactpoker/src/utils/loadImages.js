// src/utils/loadImages.js
// src/utils/loadImages.js
const cardImages = {
    'A_HEARTS.png': require('../../public/cards/A_HEARTS.png'),
    'A_SPADES.png': require('../../public/cards/A_SPADES.png'),
    'A_DIAMONDS.png': require('../../public/cards/A_DIAMONDS.png'),
    'A_CLUBS.png': require('../../public/cards/A_CLUBS.png'),

    'K_HEARTS.png': require('../../public/cards/K_HEARTS.png'),
    'K_SPADES.png': require('../../public/cards/K_SPADES.png'),
    'K_DIAMONDS.png': require('../../public/cards/K_DIAMONDS.png'),
    'K_CLUBS.png': require('../../public/cards/K_CLUBS.png'),

    'J_HEARTS.png': require('../../public/cards/J_HEARTS.png'),
    'J_SPADES.png': require('../../public/cards/J_SPADES.png'),
    'J_DIAMONDS.png': require('../../public/cards/J_DIAMONDS.png'),
    'J_CLUBS.png': require('../../public/cards/J_CLUBS.png'),
    
    'Q_HEARTS.png': require('../../public/cards/Q_HEARTS.png'),
    'Q_SPADES.png': require('../../public/cards/Q_SPADES.png'),
    'Q_DIAMONDS.png': require('../../public/cards/Q_DIAMONDS.png'),
    'Q_CLUBS.png': require('../../public/cards/Q_CLUBS.png'),

    'TEN_HEARTS.png': require('../../public/cards/TEN_HEARTS.png'),
    'TEN_SPADES.png': require('../../public/cards/TEN_SPADES.png'),
    'TEN_DIAMONDS.png': require('../../public/cards/TEN_DIAMONDS.png'),
    'TEN_CLUBS.png': require('../../public/cards/TEN_CLUBS.png'),

    'NINE_HEARTS.png': require('../../public/cards/NINE_HEARTS.png'),
    'NINE_SPADES.png': require('../../public/cards/NINE_SPADES.png'),
    'NINE_DIAMONDS.png': require('../../public/cards/NINE_DIAMONDS.png'),
    'NINE_CLUBS.png': require('../../public/cards/NINE_CLUBS.png'),

    'EIGHT_HEARTS.png': require('../../public/cards/EIGHT_HEARTS.png'),
    'EIGHT_SPADES.png': require('../../public/cards/EIGHT_SPADES.png'),
    'EIGHT_DIAMONDS.png': require('../../public/cards/EIGHT_DIAMONDS.png'),
    'EIGHT_CLUBS.png': require('../../public/cards/EIGHT_CLUBS.png'),

    'SEVEN_HEARTS.png': require('../../public/cards/SEVEN_HEARTS.png'),
    'SEVEN_SPADES.png': require('../../public/cards/SEVEN_SPADES.png'),
    'SEVEN_DIAMONDS.png': require('../../public/cards/SEVEN_DIAMONDS.png'),
    'SEVEN_CLUBS.png': require('../../public/cards/SEVEN_CLUBS.png'),

    'SIX_HEARTS.png': require('../../public/cards/SIX_HEARTS.png'),
    'SIX_SPADES.png': require('../../public/cards/SIX_SPADES.png'),
    'SIX_DIAMONDS.png': require('../../public/cards/SIX_DIAMONDS.png'),
    'SIX_CLUBS.png': require('../../public/cards/SIX_CLUBS.png'),

    
    'FIVE_HEARTS.png': require('../../public/cards/FIVE_HEARTS.png'),
    'FIVE_SPADES.png': require('../../public/cards/FIVE_SPADES.png'),
    'FIVE_DIAMONDS.png': require('../../public/cards/FIVE_DIAMONDS.png'),
    'FIVE_CLUBS.png': require('../../public/cards/FIVE_CLUBS.png'),

    'FOUR_HEARTS.png': require('../../public/cards/FOUR_HEARTS.png'),
    'FOUR_SPADES.png': require('../../public/cards/FOUR_SPADES.png'),
    'FOUR_DIAMONDS.png': require('../../public/cards/FOUR_DIAMONDS.png'),
    'FOUR_CLUBS.png': require('../../public/cards/FOUR_CLUBS.png'),

    'THREE_HEARTS.png': require('../../public/cards/THREE_HEARTS.png'),
    'THREE_SPADES.png': require('../../public/cards/THREE_SPADES.png'),
    'THREE_DIAMONDS.png': require('../../public/cards/THREE_DIAMONDS.png'),
    'THREE_CLUBS.png': require('../../public/cards/THREE_CLUBS.png'),

    'TWO_HEARTS.png': require('../../public/cards/TWO_HEARTS.png'),
    'TWO_SPADES.png': require('../../public/cards/TWO_SPADES.png'),
    'TWO_DIAMONDS.png': require('../../public/cards/TWO_DIAMONDS.png'),
    'TWO_CLUBS.png': require('../../public/cards/TWO_CLUBS.png'),
    
    

    

  




   
  };
  
  export const getCardImage = (cardName) => {
    return cardImages[cardName];
  };