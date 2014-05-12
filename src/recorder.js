/** @jsx React.DOM */
(function() {
  'use strict';

  var RecorderButton = React.createClass({
    getInitialState: function() {
      return { text: 'Record!' };
    },
    handleClick: function(e) {
      this.setState({ text: 'Recording...' });
      navigator.geolocation.getCurrentPosition(function(position) {
        this.setState({ text: 'Record!' });
        this.props.onRecorded(position);
      }.bind(this));
    },
    render: function() {
      return <button className="btn btn-lg btn-primary" onClick={this.handleClick}>
        {this.state.text}
      </button>;
    }
  });

  function handleRecorded(position) {
    console.log(position);
  }

  React.renderComponent(
    <div style={{ textAlign: 'center' }} className="col-md-12">
      <RecorderButton onRecorded={handleRecorded} />
    </div>,
    document.getElementById('my-app')
  );
}).call(this);
