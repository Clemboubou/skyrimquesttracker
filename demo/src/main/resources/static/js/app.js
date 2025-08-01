// Skyrim Quest Tracker - Vanilla JS

const apiBase = '';
const characterList = document.getElementById('character-list');
const questList = document.getElementById('quest-list');
const questSearch = document.getElementById('quest-search');
const questDifficulty = document.getElementById('quest-difficulty');
const modal = document.getElementById('modal');
const modalBody = document.getElementById('modal-body');
const closeBtn = document.querySelector('.close-btn');
const loading = document.getElementById('loading');
const errorMessage = document.getElementById('error-message');

function showLoading(show) {
  loading.classList.toggle('hidden', !show);
}
function showError(msg) {
  errorMessage.textContent = msg;
  errorMessage.classList.remove('hidden');
  setTimeout(() => errorMessage.classList.add('hidden'), 3500);
}
function openModal(html) {
  modalBody.innerHTML = html;
  modal.classList.remove('hidden');
}
function closeModal() {
  modal.classList.add('hidden');
  modalBody.innerHTML = '';
}
closeBtn.onclick = closeModal;
window.onclick = (e) => { if (e.target === modal) closeModal(); };

// Fetch Characters
async function fetchCharacters() {
  showLoading(true);
  try {
    const res = await fetch(`${apiBase}/characters`);
    if (!res.ok) throw new Error('Erreur lors du chargement des personnages');
    const data = await res.json();
    renderCharacters(data);
  } catch (e) {
    showError(e.message);
  } finally {
    showLoading(false);
  }
}
function renderCharacters(characters) {
  characterList.innerHTML = '';
  characters.forEach(c => {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
      <div class="card-title">${c.name}</div>
      <div class="card-subtitle">Race : ${c.race}</div>
      <div class="card-detail">Niveau : ${c.level}</div>
      <button class="btn-quests" data-id="${c.id}">Voir les quêtes</button>
    `;
    card.querySelector('.btn-quests').onclick = () => fetchQuestsByCharacter(c.id, c.name);
    characterList.appendChild(card);
  });
}

// Fetch Quests
async function fetchQuests() {
  showLoading(true);
  try {
    const res = await fetch(`${apiBase}/quests`);
    if (!res.ok) throw new Error('Erreur lors du chargement des quêtes');
    const data = await res.json();
    renderQuests(data);
  } catch (e) {
    showError(e.message);
  } finally {
    showLoading(false);
  }
}
function renderQuests(quests) {
  questList.innerHTML = '';
  const search = (questSearch.value || '').toLowerCase();
  const diff = questDifficulty.value;
  quests.filter(q => {
    return (!search || q.title.toLowerCase().includes(search) || q.description.toLowerCase().includes(search)) &&
           (!diff || q.difficulty === diff);
  }).forEach(q => {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
      <div class="card-title">${q.title}</div>
      <div class="card-badge">${q.difficulty}</div>
      <div class="card-detail">${q.description}</div>
      <div class="card-detail">Récompense : ${q.reward}</div>
      <button class="btn-details" data-id="${q.id}">Détails</button>
    `;
    card.querySelector('.btn-details').onclick = () => showQuestDetails(q);
    questList.appendChild(card);
  });
}
function showQuestDetails(q) {
  openModal(`
    <h3>${q.title} <span class='card-badge'>${q.difficulty}</span></h3>
    <p>${q.description}</p>
    <p><strong>Récompense :</strong> ${q.reward}</p>
  `);
}
// Fetch quests for a character
async function fetchQuestsByCharacter(charId, charName) {
  showLoading(true);
  try {
    const res = await fetch(`${apiBase}/characters/${charId}`);
    if (!res.ok) throw new Error('Impossible de charger les quêtes pour ce personnage');
    const data = await res.json();
    if (!data.quests) throw new Error('Aucune quête trouvée pour ce personnage');
    openModal(`<h3>Quêtes de ${charName}</h3><ul>` +
      data.quests.map(q => `<li>${q.title} <span class='card-badge'>${q.difficulty}</span></li>`).join('') +
      '</ul>');
  } catch (e) {
    showError(e.message);
  } finally {
    showLoading(false);
  }
}
// Listeners
questSearch.oninput = fetchQuests;
questDifficulty.onchange = fetchQuests;
// Init
fetchCharacters();
fetchQuests();
