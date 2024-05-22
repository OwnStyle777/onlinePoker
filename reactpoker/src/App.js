import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import AddPlayer from './components/AddPlayer';
import PlayerTable from './components/PlayerTable';
import './players.css'; 

const App = () => {
    const [players, setPlayers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState(null);

    const handleAddPlayer = (playerName) => {
        const newPlayer = { name: playerName, chipCount: 0 };
        setPlayers([...players, newPlayer]);
        setSelectedPlayer(null); // Skryť komponent AddPlayer po pridaní hráča
    };

    const handlePlayerClick = (playerId) => {
        setSelectedPlayer(playerId);
    };

    return (
        <div>
            <h1>Poker Game</h1>
            <div id="tableWrapper">
                {[...Array(9)].map((_, index) => (
                    <div
                        key={index}
                        id={`player${index + 1}`}
                        className="players"
                        onClick={() => handlePlayerClick(index + 1)}
                    >
                        <div className="plusIcon">+</div>
               
                        {selectedPlayer === index + 1 && (
                            <div style={{ position: 'absolute', top: '0', left: '0', backgroundColor: 'white' }}>
                                <AddPlayer onAddPlayer={handleAddPlayer} />
                            </div>
                        )}
                    </div>
                ))}
            </div>
            {players.length > 0 && <PlayerTable players={players} />}
        </div>
    );
};

export default App;

ReactDOM.render(<App />, document.getElementById('root'));