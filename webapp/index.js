localStorage['my_secure_token'] = '2c60158e-d432-4b78-a300-360cc6fa7260';
localStorage['my_user_id'] = '2c60158e-d432-4b78-a300-360cc6fa7260';

(function(){
  var API_URL_GET_CARDS = 'http://localhost:8202/api/v1/cards';
  var API_URL_GET_USER_PROFILE = 'http://localhost:8202/api/v1/user_profile';
  var API_URL_GET_PURCHASES = 'http://localhost:8202/api/v1/purchases';
  var API_URL_ = 'http://localhost:8202/api/v1/cards';

  var formatter_date_full = 'MMMM Do YYYY, h:mm:ss a';

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

    window.refresh();
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
    if(!localStorage['my_secure_token'] || !localStorage['my_user_id']){
      return false;
    }

    return true;
  }


  // methods
  function _doLogIn(){
    if(!_isLoggedIn()){
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
    var promiseAjax;
    var listEl = '#card-list';
    var url = `${API_URL_GET_CARDS}?uid=${localStorage['my_user_id']}`;
    if(!_isLoggedIn()){
      promiseAjax = Promise.reject();
    } else {
      promiseAjax = new Promise((resolve, reject) => {
        fetch(
          url,
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

    $(listEl).empty();

    promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]

      resp.forEach(function(item){
        $(`
          <tr>
            <td>${item.number}</td>
            <td>${item.code}</td>
            <td>${item.balance}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)
      })
    }).catch(() => {
      $(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
        `).appendTo(listEl)
    });
  }

  function _loadPurchase() {
    var promiseAjax;
    var listEl = '#purchase-list';
    var url = `${API_URL_GET_PURCHASES}?uid=${localStorage['my_user_id']}`;
    if(!_isLoggedIn()){
      promiseAjax = Promise.reject();
    } else {
      promiseAjax = new Promise((resolve, reject) => {
        fetch(
          url,
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

    $(listEl).empty();

    promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]

      resp.forEach(function(item){
        $(`
          <tr>
            <td>${item.number}</td>
            <td>${item.code}</td>
            <td>${item.balance}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)
      })
    }).catch(() => {
      $(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
        `).appendTo(listEl)
    });
  }
})()
