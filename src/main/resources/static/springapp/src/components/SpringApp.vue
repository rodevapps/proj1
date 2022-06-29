<template>
 <ul>
  <li v-for="error in errors">
   {{ error.message }}
  </li>
 </ul>

 <form>
  <label for="column">Choose a column: </label>
  <select v-model="column" name="column">
   <option value="kolumna1">Column 1</option>
   <option value="kolumna2">Column 2</option>
   <option value="kolumna3">Column 3</option>
   <option value="kolumna4">Column 4</option>
  </select>
  {{ " " }}
  <label for="type">Choose a type: </label>
  <select v-model="type" name="type">
   <option value="recurrent">Recurrent</option>
   <option value="unique">Unique</option>
  </select>
  {{ " " }}
  <input @click.prevent="fetchData" type="submit" value="Send">
 </form>

 <table>
  <tbody>
   <tr v-for="row in rows">
    <td>{{row.id}}</td>
    <td>{{row.kolumna1}}</td>
    <td>{{row.kolumna2}}</td>
    <td>{{row.kolumna3}}</td>
    <td>{{row.kolumna4}}</td>
   </tr>
  </tbody>
 </table>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
        column: "kolumna1",
        type: "recurrent",
        rows: [],
        errors: []
    }
  },
  methods: {
    fetchData(event) {
        axios.create({
            baseURL: "http://localhost:8080/api",
            headers: {
                "Content-type": "application/json"
            }
        }).get("/tests/" + this.type + "/" + this.column).then(response => {
            console.log(response.data);
            this.rows = response.data
        }).catch(e => {
            this.errors.push(e)
        })
    }
  }
}
</script>