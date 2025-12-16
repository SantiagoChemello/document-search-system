import { Component,  } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FilesComponent } from './files/files.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FilesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'CandidatesWeb';
}
