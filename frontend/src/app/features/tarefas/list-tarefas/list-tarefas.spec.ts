import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTarefas } from './list-tarefas';

describe('ListTarefas', () => {
  let component: ListTarefas;
  let fixture: ComponentFixture<ListTarefas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListTarefas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListTarefas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
