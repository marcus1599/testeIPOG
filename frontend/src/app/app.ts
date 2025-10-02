import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  providers: [MessageService],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'frontend';
  constructor(private messageService: MessageService) {}

  showSuccess() {
    this.messageService.add({severity:'success', summary:'Sucesso', detail:'Operação realizada'});
  }
}
