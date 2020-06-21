// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
    
  const facts = [
    'I taught myself surfing, skateboard, photography.',
    'I sat in a river and made a toe brush out of some twigs, seamoss and leaf stalks',
    'I tried to make a raft out random pieces of wood.',
    'I want a husky dog when I get older.',
    'I want to be financially free and travel the world.',
    'I snorkelled under a huge bat cave that was located on an islet near an island that was off the coast of West Africa.',
    'I lived in a small civilization in the desert for a few days (there were so many stars.)',
    'A police officer once told me he was hoping that warrants would come up when he flagged my ID',
    'I went sky diving and I gotta do it again.',
    'I like to go waterfall hunting in Jamaica.',
    'I visited a remote island for 5 days and only met 3 decent english speakers. LET ME TELL YOU, Google Translate saves lives.',
    'I am a descendant of the Maroons from the Cockpit Country (slaves that escaped plantations and resisted).',
    'Growing up, I thought only rich people had heated water in their pipes.',
    'When I bathed in a river for the first time, I never wanted to use showers again.',
    'I rode & bonded with a camel and it immediately tried to kiss me (Animals understand energy).',
    'I went on a hike and jumped into a random creek with my friends and played with the tadpoles',
    'I traveled to 3 countries (outside of Jamaica & USA) for 5 days, 5 weeks and 5 months.',
  ]

  // Pick a random greeting.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

// async function getRandomQuoteUsingAsyncAwait() {
//   const response = await fetch('/data');
//   const quote = await response.text();
//   document.getElementById('quote-container').innerText = quote;
// }

function getCommentStats(){
    fetch("/comments").then(response => response.json()).then((comments) => {
        const commentHistory = document.getElementById('comment-history');
        comments.forEach((comment) => {
            console.log(comment)
            commentHistory.appendChild(createCommentElement(comment));
        });
    });
}

/** Creates an element that represents a task, including its delete button. */
function createCommentElement(comment) {
  const commentElement = document.createElement('li');
  commentElement.className = 'comment';

  const textElement = document.createElement('span');
  textElement.innerText = comment.text;

//   const deleteButtonElement = document.createElement('button');
//   deleteButtonElement.innerText = 'Delete';
//   deleteButtonElement.addEventListener('click', () => {
//     deleteTask(comment);

//     // Remove the task from the DOM.
//     commentElement.remove();
//   });

  commentElement.appendChild(textElement);
//   commentElement.appendChild(deleteButtonElement);
  return commentElement;
}

/** Tells the server to delete the task. */
function deleteTask(task) {
  const params = new URLSearchParams();
  params.append('id', task.id);
  fetch('/delete-task', {method: 'POST', body: params});
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
