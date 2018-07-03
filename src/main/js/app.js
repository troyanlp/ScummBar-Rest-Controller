const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

const PageHeader = require('react-bootstrap').PageHeader;
const Panel = require('react-bootstrap').Panel;
const Table = require('react-bootstrap').Table;
const Button = require('react-bootstrap').Button;
const Modal = require('react-bootstrap').Modal;
const FormGroup = require('react-bootstrap').FormGroup;
const FormControl = require('react-bootstrap').FormControl;
const ControlLabel = require('react-bootstrap').ControlLabel;

class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {boards: []};
		this.onBoardDelete = this.onBoardDelete.bind(this);
		this.onBoardCreate = this.onBoardCreate.bind(this);
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/boards'}).done(response => {
			this.setState({boards: response.entity._embedded.boards});
		});
	}

	onBoardDelete(board) {
		client({method: 'DELETE', path: board._links.self.href}).done(response => {
			this.componentDidMount();
		});
	}

	onBoardCreate(name) {
	    var board = {name};
		client({method: 'POST', path: '/v1/board', entity: board, headers: {'Content-Type': 'application/json'}}).done(response => {
			this.componentDidMount();
		});
	}

	render() {
		return (
		    <div>
		        <PageHeader>Board CRUD</PageHeader>
                <Panel header="Boards List">
                    <BoardList boards={this.state.boards} onBoardDelete={this.onBoardDelete}
                        onAddLocationToBoard={this.onAddLocationToBoard}
                        onDeleteLocationFromBoard={this.onDeleteLocationFromBoard}/>
                    <CreateBoardDialog onBoardCreate={this.onBoardCreate}/>
		        </Panel>
			</div>
		)
	}
}

class BoardList extends React.Component {
	render() {
		var boards = this.props.boards.map(board =>
			<Board key={board._links.self.href} board={board} onBoardDelete={this.props.onBoardDelete}/>
		);
		return (
			<Table striped bordered condensed hover>
			    <thead>
                    <tr>
                        <th>Boards</th>
                        <th>Delete</th>
                    </tr>
                </thead>
				<tbody>
					{boards}
				</tbody>
			</Table>
		)
	}
}

class Board extends React.Component {
	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onBoardDelete(this.props.board);
	}

	render() {
		return (
			<tr>
				<td>{this.props.board.name}</td>
				<td><Button bsStyle="danger" onClick={this.handleDelete}>Delete</Button></td>
			</tr>
		)
	}
}

class CreateBoardDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showModal: false, boardName: '' };
        this.close = this.close.bind(this);
        this.open = this.open.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleBoardName = this.handleBoardName.bind(this);
    }

    close() {
        this.setState({ showModal: false });
    }

    open() {
        this.setState({ showModal: true });
    }

    handleSubmit() {
        this.props.onBoardCreate(this.state.boardName);
        this.setState({ showModal: false, boardName : '' });
    }

    handleBoardName(e) {
        this.setState({ boardName: e.target.value });
    }

    render() {
        return (
        <div>
            <Button bsStyle="primary" onClick={this.open}>Add board</Button>
            <Modal show={this.state.showModal} onHide={this.close}>
                <Modal.Header closeButton>
                    <Modal.Title>Create new Board</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <FormGroup>
                            <ControlLabel>Board Name</ControlLabel>
                            <FormControl type="text" placeholder="Board Name" value={this.state.boardName} onChange={this.handleBoardName} />
                        </FormGroup>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.handleSubmit}>Submit</Button>
                </Modal.Footer>
            </Modal>
        </div>
        );
    }
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
