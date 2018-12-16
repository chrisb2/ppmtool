import { GET_ERRORS } from "../actions/types";

const initialState = {};

export default function(status = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;
    default:
      return initialState;
  }
}
