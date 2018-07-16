(function(){
  var api_host = '';
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
  }


  // state
  function _isLoggedIn(){
    return !!data.user;
  }


  // methods
  function _doLogIn(){
    data = {
      user: null,
      cards: [],
      purchases: [],
    };

    return Promise.resolve();
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
