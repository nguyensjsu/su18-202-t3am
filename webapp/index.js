localStorage['my_secure_token'] = '2c60158e-d432-4b78-a300-360cc6fa7260';
localStorage['my_user_id'] = '2c60158e-d432-4b78-a300-360cc6fa7260';

(function(){
  var API_URL_GET_CARDS = 'http://localhost:8202/api/v1/cards';
  var API_URL_GET_USER_PROFILE = 'http://localhost:8202/api/v1/user_profile';
  var API_URL_ = 'http://localhost:8202/api/v1/cards';
  var API_URL_ = 'http://localhost:8202/api/v1/cards';

  var data = {
    user: null,
    cards: [],
    purchases: [],
  }

  $( function() {
    $( "#tabs" ).tabs();
    _doLogIn().then(_onLoggedIn, _onLoggedOff);
  } );

  // exposed...
  window.logoff = function(){
    _onLoggedOff();
  }


  window.login = function(){
    _onLoggedIn();
  }

  window.doOpenNewCard = _doOpenNewCard;
  window.doOpenNewPurchase = _doOpenNewPurchase;

  window.refresh = function(){
    _loadCards();
    _loadPurchase();
  }

  // events...
  function _onLoggedIn() {
    // alert('Logging In');

    $('#page-login').hide();
    $('#page-authenticated').show();
  }

  function _onLoggedOff() {
    // alert('Logging out');

    $('#page-login').show();
    $('#page-authenticated').hide();

    // reset
    data = {
      user: null,
      cards: [],
      purchases: [],
    };


    delete localStorage['my_secure_token'];
    delete localStorage['my_user_id'];
  }


  // state
  function _isLoggedIn(){
    return !!data.user;
  }


  // methods
  function _doLogIn(){
    if(!localStorage['my_secure_token'] || !localStorage['my_user_id']){
      return Promise.reject();
    }



    return new Promise((resolve, reject) => {
      fetch(
        `${API_URL_GET_USER_PROFILE}?uid=${localStorage['my_user_id']}`,
        {
          headers:{
            'Content-Type': 'application/json'
          }
        }
      )
        .then(res => res.json())
        .catch(reject)
        .then(resolve);
    });
  }


  function _doOpenNewCard(){
    alert('add new card')
  }

  function _doNewCard(req){

  }

  function _doOpenNewPurchase(){
    alert('add new purchase')
  }

  function _doNewPurchase(req){

  }


  function _loadCards() {

  }

  function _loadPurchase() {

  }
})()
