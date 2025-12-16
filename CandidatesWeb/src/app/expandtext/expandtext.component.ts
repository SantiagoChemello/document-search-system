import { Component, Input, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-expandtext',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './expandtext.component.html',
  styleUrl: './expandtext.component.css'
})
export class ExpandtextComponent implements OnInit{

  @Input() text: string = '';
  @Input() limit: number = 0;
  showMore: boolean;

  constructor(){
    this.showMore = false
  }


  ngOnInit(): void {
  }
}
