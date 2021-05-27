import { PolymerElement, html } from "@polymer/polymer/polymer-element";

class UsersListComponent extends PolymerElement {
  static get template() {
    return html`
      <style>
        :host {
          width: 100%;
        }
        .table-style {
          border-collapse: collapse;
          width: 100%;
        }
        .th-style,
        .td-style {
          text-align: center;
          border: 1px solid #dddddd;
          padding: 8px;
        }
      </style>
      <div style="width: 100%">
        <table class="table-style">
          <tr on-click="processElement">
            <th class="th-style">First Name</th>
            <th class="th-style">Last Name</th>
            <th class="th-style">Handle</th>
            <th class="th-style">Email</th>
          </tr>
          <template is="dom-repeat" items="[[users]]">
            <tr on-click="handleClick" id="[[item.firstName]]">
              <td class="td-style">{{item.firstname}}</td>
              <td class="td-style">{{item.lastname}}</td>
              <td class="td-style">{{item.handle}}</td>
              <td class="td-style">{{item.email}}</td>
            </tr>
          </template>
        </table>
      </div>
    `;
  }
}
customElements.define("users-list-component", UsersListComponent);
